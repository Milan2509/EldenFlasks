package eagleseye.elden_flasks.internals.item;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.item.custom.EnhancerItem;
import eagleseye.elden_flasks.internals.item.custom.EnhancerType;
import eagleseye.elden_flasks.internals.item.custom.HealingFlaskItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EldenFlasksItems {
    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, "healing_flask"), new HealingFlaskItem());
        Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, "enhancer_uses"), new EnhancerItem(EnhancerType.USES));
        Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, "enhancer_healing"), new EnhancerItem(EnhancerType.HEALING));
    }
}
