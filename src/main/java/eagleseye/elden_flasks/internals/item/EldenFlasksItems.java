package eagleseye.elden_flasks.internals.item;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.item.custom.EnhancerItem;
import eagleseye.elden_flasks.internals.item.custom.EnhancerType;
import eagleseye.elden_flasks.internals.item.custom.HealingFlaskItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EldenFlasksItems {
    public static final Item HEALING_FLASK = registerItem("healing_flask", new HealingFlaskItem());
    public static final Item ENHANCER_USES = registerItem("enhancer_uses", new EnhancerItem(EnhancerType.USES));
    public static final Item ENHANCER_HEALING = registerItem("enhancer_healing", new EnhancerItem(EnhancerType.HEALING));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, name), item);
    }

    public static void register() {
        EldenFlasks.LOGGER.info("Registering Items for: " + EldenFlasks.MOD_ID);
    }
}
