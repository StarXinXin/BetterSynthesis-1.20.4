package top.xinstudio.xinxin;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import top.xinstudio.xinxin.block.ModBlocks;
import top.xinstudio.xinxin.screen.BsFurnaceScreen;
import top.xinstudio.xinxin.screen.BsSaucepanScreen;
import top.xinstudio.xinxin.screen.ModScreenHandlers;


@Environment(EnvType.CLIENT)
public class BetterSynthesisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.FURNACE_SCREEN_HANDLER, BsFurnaceScreen::new);
        HandledScreens.register(ModScreenHandlers.BsSaucepan_SCREEN_HANDLER, BsSaucepanScreen::new);


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.Cabbage_CROP, RenderLayer.getCutout());

//        HandledScreens.register(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, CookingPotScreen::new);
    }
}
