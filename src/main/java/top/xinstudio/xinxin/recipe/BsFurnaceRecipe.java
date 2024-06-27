package top.xinstudio.xinxin.recipe;



import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class BsFurnaceRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public BsFurnaceRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()){
            return false;
        }
        return recipeItems.getFirst().test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BsFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "bs_furnace";
    }


        public static class Serializer implements RecipeSerializer<BsFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "bs_furnace";

        public static final Codec<BsFurnaceRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredients").forGetter(BsFurnaceRecipe::getIngredients),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, BsFurnaceRecipe::new));

        @SuppressWarnings("SameParameterValue")
        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "食谱的成分太多了！") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "食谱没有成分！") : DataResult.success(list));
        }

        @Override
        public Codec<BsFurnaceRecipe> codec() {
            return CODEC;
        }

        @Override
        public BsFurnaceRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();
            return new BsFurnaceRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, BsFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.getResult(null));
        }
    }
}


//import com.mojang.serialization.Codec;
//import com.mojang.serialization.DataResult;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.fabricmc.fabric.impl.recipe.ingredient.ShapelessMatch;
//import net.minecraft.inventory.SimpleInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.PacketByteBuf;
//import net.minecraft.recipe.Ingredient;
//import net.minecraft.recipe.Recipe;
//import net.minecraft.recipe.RecipeSerializer;
//import net.minecraft.recipe.RecipeType;
//import net.minecraft.registry.DynamicRegistryManager;
//import net.minecraft.util.collection.DefaultedList;
//import net.minecraft.util.dynamic.Codecs;
//import net.minecraft.world.World;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BsFurnaceRecipe implements Recipe<SimpleInventory> {
//    private final ItemStack output;
//    private final List<Ingredient> recipeItems;
//
//    public BsFurnaceRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
//        this.output = itemStack;
//        this.recipeItems = ingredients;
//    }
//
//    @Override
//    public boolean isIgnoredInRecipeBook() {
//        return true;
//    }
//
//    @Override
//    @SuppressWarnings("UnstableApiUsage")
//    public boolean matches(SimpleInventory inventory, World world) {
//        if(world.isClient()) return false;
//        List<ItemStack> inputs = new ArrayList<>();
//        for(int j = 0; j < 9; ++j) {
//            ItemStack itemstack = inventory.getStack(j);
//            if (!itemstack.isEmpty()) {
//                inputs.add(itemstack);
//            }
//        }
//        return inputs.size() == this.recipeItems.size() &&
//                ShapelessMatch.isMatch(inputs, this.recipeItems);
//    }
//
//    @Override
//    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
//        return output;
//    }
//
//    @Override
//    public boolean fits(int width, int height) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResult(DynamicRegistryManager registryManager) {
//        return output;
//    }
//
//    @Override
//    public DefaultedList<Ingredient> getIngredients() {
//        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
//        list.addAll(recipeItems);
//        return list;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return Serializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return Type.INSTANCE;
//    }
//
//    public static class Type implements RecipeType<BsFurnaceRecipe> {
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "bs_furnace";
//    }
//
//    public static class Serializer implements RecipeSerializer<BsFurnaceRecipe> {
//        public static final Serializer INSTANCE = new Serializer();
//        public static final String ID = "bs_furnace";
//
//        public static final Codec<BsFurnaceRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
//                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredients").forGetter(BsFurnaceRecipe::getIngredients),
//                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
//        ).apply(in, BsFurnaceRecipe::new));
//
//        @SuppressWarnings("SameParameterValue")
//        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
//            return Codecs.validate(Codecs.validate(
//                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "食谱的成分太多了！") : DataResult.success(list)
//            ), list -> list.isEmpty() ? DataResult.error(() -> "食谱没有成分！") : DataResult.success(list));
//        }
//
//        @Override
//        public Codec<BsFurnaceRecipe> codec() {
//            return CODEC;
//        }
//
//        @Override
//        public BsFurnaceRecipe read(PacketByteBuf buf) {
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
//            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
//            ItemStack output = buf.readItemStack();
//            return new BsFurnaceRecipe(inputs, output);
//        }
//
//        @Override
//        public void write(PacketByteBuf buf, BsFurnaceRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//            for (Ingredient ingredient : recipe.getIngredients()) {
//                ingredient.write(buf);
//            }
//            buf.writeItemStack(recipe.getResult(null));
//        }
//    }
//}