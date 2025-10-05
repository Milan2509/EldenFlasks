package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import eagleseye.eldenflasks.item.FlaskBuffItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class BuffRegistry {
    public static final FlaskBuff MOVEMENT_SPEED = register("movement_speed",
            "minecraft:generic.movement_speed",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c"),
            0.05
    );

    public static final FlaskBuff ATTACK_DAMAGE = register("attack_damage",
            "minecraft:generic.attack_damage",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("52e6ada6-588d-4457-b539-3628a605a94e"),
            0.05
    );

    public static final FlaskBuff ARMOR = register("armor",
            "minecraft:generic.armor",
            2,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("138932d2-9959-4c9f-b5da-f581e499324f"),
            2
    );

    public static final FlaskBuff TOUGHNESS = register("toughness",
            "minecraft:generic.armor_toughness",
            1,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("1391114c-ec2a-46d1-b626-467323cac761"),
            1
    );

    public static final FlaskBuff MAX_HEALTH = register("max_health",
            "minecraft:generic.max_health",
            4,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("a40afabb-35a2-40b6-8322-adce9901f1c3"),
            2
    );

    public static final FlaskBuff ATTACK_SPEED = register("attack_speed",
            "minecraft:generic.attack_speed",
            0.02,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("6270e2e6-45e5-4b23-be87-21b788572d47"),
            0.02

    );

    public static final FlaskBuff LUCK = register("luck",
            "minecraft:generic.luck",
            2,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("a57a00b6-09d2-4099-98e9-4f684f0c3457"),
            1

    );

    public static final FlaskBuff KNOCKBACK_RES = register("knockback_res",
            "minecraft:generic.knockback_resistance",
            0.1,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("9663d9d8-49a0-41f2-8688-8721e30accc3"),
            0.05
    );

    // MODDED
    private static final String SPELL_POWER = "spell_power";
    private static final String ETERNAL_ATTRIBUTES = "eternal_attributes";
    private static final String RANGED_WEAPON = "ranged_weapon_api";
    private static final String COMBAT_ROLL = "combatroll";

    // Spell Power
    public static final FlaskBuff FIRE = registerModded(SPELL_POWER, "fire_power",
            "spell_power:fire",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("ec3edbe8-7fe2-48ef-b9e5-b54ca30f2500"),
            0.05
    );
    public static final FlaskBuff FROST = registerModded(SPELL_POWER, "frost_power",
            "spell_power:frost",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("f6ff3755-e55a-4fe5-9c0c-02245c663725"),
            0.05

    );
    public static final FlaskBuff ARCANE = registerModded(SPELL_POWER, "arcane_power",
            "spell_power:arcane",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("0aefec74-21e7-4448-8358-037e8b9c5cdd"),
            0.05

    );
    public static final FlaskBuff HEALING = registerModded(SPELL_POWER, "healing_power",
            "spell_power:healing",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("fc37458d-e073-4104-8d63-cb909773beba"),
            0.05
    );
    // Eternal Attributes
    public static final FlaskBuff UNHOLY = registerModded(ETERNAL_ATTRIBUTES, "unholy_power",
            "eternal_attributes:unholy",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("abcd7c12-1c0c-4f84-a01a-7ecebaada56c"),
            0.05

    );
    public static final FlaskBuff BLOOD = registerModded(ETERNAL_ATTRIBUTES, "blood_power",
            "eternal_attributes:blood",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("f502d5e0-5071-44ba-8e9c-fa006ff5fe30"),
            0.05
    );
    // Ranged Weapon
    public static final FlaskBuff RANGED_DAMAGE = registerModded(RANGED_WEAPON, "ranged_damage",
            "ranged_weapon:damage",
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("88bde36d-2520-451f-a55d-0207e54847a1"),
            0.05
    );
    public static final FlaskBuff DRAW_SPEED = registerModded(RANGED_WEAPON, "draw_speed",
            "ranged_weapon:haste",
            0.02,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("97200f22-94b8-4bd2-aaf9-01d44b10cde9"),
            0.02
    );
    // Combat Roll
    public static final FlaskBuff ROLL_RECHARGE = registerModded(COMBAT_ROLL, "roll_recharge",
            "combatroll:recharge",
            0.1,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("6711b554-3a45-4ace-b541-9df8045d201f"),
            0.02

    );
    public static final FlaskBuff ROLL_COUNT = registerModded(COMBAT_ROLL, "roll_count",
            "combatroll:count",
            1,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("6711b554-3a45-4ace-b541-9df8045d201f"),
            0.02
    );

    private static FlaskBuff register(String name, String attribute, double value,
                                      EntityAttributeModifier.Operation operation, UUID uuid, double tierIncrement) {

        FlaskBuff flaskBuff = createFlaskBuff(name, attribute, value, operation, uuid);
//        FlaskBuff flaskBuff_t2 = createFlaskBuff(name + "_2", attribute, value + tierIncrement, operation, uuid);
//        FlaskBuff flaskBuff_t3 = createFlaskBuff(name + "_3", attribute, value + tierIncrement * 2, operation, uuid);

        addBuffItem(name);
//        addBuffItem(name + "_2");
//        addBuffItem(name + "_3");

        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), flaskBuff);
//        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name + "_2"), flaskBuff_t2);
//        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name + "_3"), flaskBuff_t3);

        return flaskBuff;
    }

    private static FlaskBuff registerModded(String requiredMod, String name, String attribute, double value,
                                            EntityAttributeModifier.Operation operation, UUID uuid, double tierIncrement) {
        if (!FabricLoader.getInstance().isModLoaded(requiredMod)) return null;

        return register(name, attribute, value, operation, uuid, tierIncrement);
    }

    private static void addBuffItem(String name) {
        if (Registries.ITEM.containsId(new Identifier(EldenFlasks.MOD_ID, "orb_" + name))) return;


        Item buffItem = Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, "orb_" + name), new FlaskBuffItem(new FabricItemSettings(),
                EldenFlasks.MOD_ID + ":" + name));

        FlaskGroup.addBuffItem(buffItem);
    }

    private static FlaskBuff createFlaskBuff(String name, String attribute, double value,
                                             EntityAttributeModifier.Operation operation, UUID uuid) {
        return new FlaskBuff(
                "buff.eldenflasks." + name,
                getAttributeFromString(attribute),
                value,
                operation,
                uuid
        );
    }

    private static EntityAttribute getAttributeFromString(String id) {
        return Registries.ATTRIBUTE.get(new Identifier(StringUtils.substringBefore(id, ":"), StringUtils.substringAfter(id, ":")));
    }

    public static void init() {
    }
}
