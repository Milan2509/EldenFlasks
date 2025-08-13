package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.screen.FlaskMixerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlerRegistry {
    public static final ScreenHandlerType<FlaskMixerScreenHandler> FLASK_MIXER_SCREEN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(EldenFlasks.MOD_ID, "flask_mixing"),
                    new ExtendedScreenHandlerType<>(FlaskMixerScreenHandler::new));

    public static void init(){}
}
