package eagleseye.eldenflasks;

import eagleseye.eldenflasks.screen.FlaskMixerScreen;
import eagleseye.eldenflasks.screen.ScreenHandlerRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class EldenFlasksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ScreenHandlerRegistry.FLASK_MIXER_SCREEN_SCREEN_HANDLER, FlaskMixerScreen::new);
    }
}
