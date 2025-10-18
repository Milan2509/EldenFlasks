package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {
    public static final Item HEALTH_FLASK = register("health_flask",
            new HealingFlaskItem(new FabricItemSettings()));
    public static final Item MIXING_FLASK = register("mixing_flask",
            new MixingFlaskItem(new FabricItemSettings()));

    //Enhancer Items
    //Charge Enhancers
    private static final int chargeModifier = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.maxChargeModifier();
    private static final int chargeLimit = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.maxChargeLimit();

    public static final Item SACRED_TEARS = register("sacred_tears", new ChargeEnhancerItem(new FabricItemSettings(),
            chargeModifier, chargeLimit, "A goblet containing the sacred tears of the mourning divine."));

    //Healing Enhancers
    private static final float healingModifier = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.healingModifier();
    private static final float healingLimit = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.healingLimit();

    public static final Item RUNE = register("rune", new HealingEnhancerItem(new FabricItemSettings(),
            healingModifier, healingLimit, "A rune forged by the dwarves and inscribed by powerful mages."));

    //TODO: re-add pearl, now increasing the duration of the mixing flask


    //Buff Items
    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, id), item);
    }

    public static void init() {
    }
}
