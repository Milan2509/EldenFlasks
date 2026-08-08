package eagleseye.elden_flasks;

import eagleseye.elden_flasks.internals.client.ModelPredicates;
import net.fabricmc.api.ClientModInitializer;

public class EldenFlasksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicates.register(); //Doesn't need to go in client initializer?
    }
}
