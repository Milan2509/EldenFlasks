package eagleseye.elden_flasks.internals.client;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.item.EldenFlasksItems;
import eagleseye.elden_flasks.internals.util.ComponentUtils;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModelPredicates {
    public static void register() {
        ModelPredicateProviderRegistry.register(
                EldenFlasksItems.HEALING_FLASK,
                Identifier.of(EldenFlasks.MOD_ID, "uses"),
                (stack, world, entity, seed) -> getChargePercentage(stack)
        );
    }

    private static float getChargePercentage(ItemStack stack) {
        int currentUses = ComponentUtils.getCurrentUses(stack);
        int maxUses = ComponentUtils.getMaxUses(stack);

        float percentage = (float) currentUses / maxUses;

        if (percentage >= 1.0f) {
            return 1.0f;
        } else if (percentage > 0.5f) {
            return 0.75f;
        } else if (percentage > 0.0) {
            return 0.5f;
        } else {
            return 0.0f;
        }

    }
}
