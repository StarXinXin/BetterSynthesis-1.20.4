package top.xinstudio.xinxin.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModScreenHandlers {


    public static final ScreenHandlerType<BsFurnaceScreenHandler> FURNACE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(BetterSynthesis.MOD_ID, "bs_furnace"),
                    new ExtendedScreenHandlerType<>(BsFurnaceScreenHandler::new));
    public static void registerScreenHandlers(){

    }
}
