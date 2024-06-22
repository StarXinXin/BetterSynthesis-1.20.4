package top.xinstudio.xinxin.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CookingPotScreenHandler> POLISHING_MACHINE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER,new Identifier(BetterSynthesis.MOD_ID,"cooking_pot"),
                    new ExtendedScreenHandlerType<>(CookingPotScreenHandler::new));
    public static void registerScreenHandlers(){

    }
}
