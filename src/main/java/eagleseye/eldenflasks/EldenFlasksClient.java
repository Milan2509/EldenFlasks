package eagleseye.eldenflasks;

import eagleseye.eldenflasks.registry.ItemRegistry;
import eagleseye.eldenflasks.screen.FlaskMixerScreen;
import eagleseye.eldenflasks.registry.ScreenHandlerRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class EldenFlasksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ScreenHandlerRegistry.FLASK_MIXER_SCREEN_SCREEN_HANDLER, FlaskMixerScreen::new);

        //new model predicate
        ModelPredicateProviderRegistry.register(
                ItemRegistry.HEALTH_FLASK,
                new Identifier("charges"),
                (stack, world, entity, seed) -> {
                    if (stack.hasNbt() && stack.getNbt().contains("charges")) {
                        int charges = stack.getNbt().getInt("charges");
                        int max = stack.getOrCreateNbt().getInt("maxCharges");

                        return (float) charges / max; // returns a float between 0.0 and 1.0

                    }
                    return 1f; // default
                }
        );
    }
}
