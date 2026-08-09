package eagleseye.elden_flasks.internals;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.item.custom.HealingFlaskItem;
import eagleseye.elden_flasks.internals.util.ComponentUtils;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.item.ItemStack;

public class EldenFlasksEvents {
    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if(!EldenFlasks.flasksConfig.healing_flask.death_recharge_flask) return;

            for (ItemStack stack : newPlayer.getInventory().main) {
                if (stack.getItem() instanceof HealingFlaskItem) {
                    ComponentUtils.setCurrentUses(stack, ComponentUtils.getMaxUses(stack));
                }
            }
        });
    }
}
