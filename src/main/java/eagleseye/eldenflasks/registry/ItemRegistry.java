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
//    public static final Item MIXING_FLASK = register("mixing_flask",
//            new MixingFlaskItem(new FabricItemSettings()));

    //Enhancer Items
    //Charge Enhancers
    private static int chargeModifier = EldenFlasks.FLASKS_CONFIG.maxChargeModifier();
    private static int chargeLimit = EldenFlasks.FLASKS_CONFIG.maxChargeLimit();

    public static final Item SACRED_TEARS = register("sacred_tears", new ChargeEnhancerItem(new FabricItemSettings(),
            chargeModifier, chargeLimit, "A goblet containing the sacred tears of the mourning divine."));

    //Healing Enhancers
    private static float healingModifier = EldenFlasks.FLASKS_CONFIG.healingModifier();
    private static float healingLimit = EldenFlasks.FLASKS_CONFIG.healingLimit();

    public static final Item RUNE = register("rune", new HealingEnhancerItem(new FabricItemSettings(),
            healingModifier, healingLimit, "A rune forged by the dwarves and inscribed by powerful mages."));

    //Pearl Enhancers
    private static int drinkTimeModifier = EldenFlasks.FLASKS_CONFIG.drinkTimeModifier();
    private static int drinkTimeLimit = EldenFlasks.FLASKS_CONFIG.drinkTimeLimit();

    public static final Item PEARL = register("pearl", new DrinkEnhancerItem(new FabricItemSettings(),
            drinkTimeModifier, drinkTimeLimit, "This pearl is rumoured to hold the souls of fallen pirates."));

    //Buff Items
    private static Item register(String id, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, id), item);
    }
    public static void init(){}
}
