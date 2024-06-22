package top.xinstudio.xinxin.recipe;

import net.minecraft.item.Item;

public class CookingRecipe {
    private final Item inputItem;
    private final Item outputItem;
    private final int outputAmount;

    public CookingRecipe(Item inputItem, Item outputItem, int outputAmount) {
        this.inputItem = inputItem;
        this.outputItem = outputItem;
        this.outputAmount = outputAmount;
    }

    public Item getInputItem() {
        return inputItem;
    }

    public Item getOutputItem() {
        return outputItem;
    }

    public int getOutputAmount() {
        return outputAmount;
    }
}