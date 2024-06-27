package top.xinstudio.xinxin.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModRecipes {
    public static void registerRecipes(){
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(BetterSynthesis.MOD_ID, BsFurnaceRecipe.Serializer.ID),
                BsFurnaceRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(BetterSynthesis.MOD_ID, BsFurnaceRecipe.Type.ID),
                BsFurnaceRecipe.Type.INSTANCE);
    }
}
