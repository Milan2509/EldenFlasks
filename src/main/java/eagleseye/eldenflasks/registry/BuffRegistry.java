package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import eagleseye.eldenflasks.item.FlaskBuffItem;
import eagleseye.eldenflasks.util.FlaskBuffUtils;
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

import static eagleseye.eldenflasks.EldenFlasks.BUFF_CONFIG;

public class BuffRegistry {
    public static final FlaskBuff MOVEMENT_SPEED = register("movement_speed",
            "minecraft:generic.movement_speed",
            BUFF_CONFIG.movementSpeedValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.movementSpeedOperation()),
            UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c")
    );

    public static final FlaskBuff ATTACK_DAMAGE = register("attack_damage",
            "minecraft:generic.attack_damage",
            BUFF_CONFIG.attackDamageValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.attackDamageOperation()),
            UUID.fromString("52e6ada6-588d-4457-b539-3628a605a94e")
    );

    public static final FlaskBuff ARMOR = register("armor",
            "minecraft:generic.armor",
            BUFF_CONFIG.armorValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.armorOperation()),
            UUID.fromString("138932d2-9959-4c9f-b5da-f581e499324f")
    );

    public static final FlaskBuff TOUGHNESS = register("toughness",
            "minecraft:generic.armor_toughness",
            BUFF_CONFIG.toughnessValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.toughnessOperation()),
            UUID.fromString("1391114c-ec2a-46d1-b626-467323cac761")
    );

    public static final FlaskBuff MAX_HEALTH = register("max_health",
            "minecraft:generic.max_health",
            BUFF_CONFIG.maxHealthValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.maxHealthOperation()),
            UUID.fromString("a40afabb-35a2-40b6-8322-adce9901f1c3")
    );

    public static final FlaskBuff ATTACK_SPEED = register("attack_speed",
            "minecraft:generic.attack_speed",
            BUFF_CONFIG.attackSpeedValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.attackSpeedOperation()),
            UUID.fromString("6270e2e6-45e5-4b23-be87-21b788572d47")
    );

    public static final FlaskBuff LUCK = register("luck",
            "minecraft:generic.luck",
            BUFF_CONFIG.luckValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.luckOperation()),
            UUID.fromString("a57a00b6-09d2-4099-98e9-4f684f0c3457")
    );

    public static final FlaskBuff KNOCKBACK_RES = register("knockback_res",
            "minecraft:generic.knockback_resistance",
            BUFF_CONFIG.knockbackResistanceValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.knockbackResistanceOperation()),
            UUID.fromString("9663d9d8-49a0-41f2-8688-8721e30accc3")
    );

    // MODDED
    private static final String SPELL_POWER = "spell_power";
    private static final String ETERNAL_ATTRIBUTES = "eternal_attributes";
    private static final String RANGED_WEAPON = "ranged_weapon_api";
    private static final String COMBAT_ROLL = "combatroll";

    // Spell Power
    //TODO: Add following buffs: Spell Haste, Spell Critical Chance, Spell Critical Damage
    public static final FlaskBuff FIRE = registerModded(SPELL_POWER, "fire_power",
            "spell_power:fire",
            BUFF_CONFIG.firePowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.firePowerOperation()),
            UUID.fromString("ec3edbe8-7fe2-48ef-b9e5-b54ca30f2500")
    );
    public static final FlaskBuff FROST = registerModded(SPELL_POWER, "frost_power",
            "spell_power:frost",
            BUFF_CONFIG.frostPowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.frostPowerOperation()),
            UUID.fromString("f6ff3755-e55a-4fe5-9c0c-02245c663725")
    );
    public static final FlaskBuff ARCANE = registerModded(SPELL_POWER, "arcane_power",
            "spell_power:arcane",
            BUFF_CONFIG.arcanePowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.arcanePowerOperation()),
            UUID.fromString("0aefec74-21e7-4448-8358-037e8b9c5cdd")
    );
    public static final FlaskBuff HEALING = registerModded(SPELL_POWER, "healing_power",
            "spell_power:healing",
            BUFF_CONFIG.healingPowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.healingPowerOperation()),
            UUID.fromString("fc37458d-e073-4104-8d63-cb909773beba")
    );
    // Eternal Attributes
    public static final FlaskBuff UNHOLY = registerModded(ETERNAL_ATTRIBUTES, "unholy_power",
            "eternal_attributes:unholy",
            BUFF_CONFIG.unholyPowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.unholyPowerOperation()),
            UUID.fromString("abcd7c12-1c0c-4f84-a01a-7ecebaada56c")
    );
    public static final FlaskBuff BLOOD = registerModded(ETERNAL_ATTRIBUTES, "blood_power",
            "eternal_attributes:blood",
            BUFF_CONFIG.bloodPowerValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.bloodPowerOperation()),
            UUID.fromString("f502d5e0-5071-44ba-8e9c-fa006ff5fe30")
    );
    // Ranged Weapon
    public static final FlaskBuff RANGED_DAMAGE = registerModded(RANGED_WEAPON, "ranged_damage",
            "ranged_weapon:damage",
            BUFF_CONFIG.rangedDamageValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.rangedDamageOperation()),
            UUID.fromString("88bde36d-2520-451f-a55d-0207e54847a1")
    );
    public static final FlaskBuff DRAW_SPEED = registerModded(RANGED_WEAPON, "draw_speed",
            "ranged_weapon:haste",
            BUFF_CONFIG.drawSpeedValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.drawSpeedOperation()),
            UUID.fromString("97200f22-94b8-4bd2-aaf9-01d44b10cde9")
    );
    // Combat Roll
    public static final FlaskBuff ROLL_RECHARGE = registerModded(COMBAT_ROLL, "roll_recharge",
            "combatroll:recharge",
            BUFF_CONFIG.rollRechargeValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.rollRechargeOperation()),
            UUID.fromString("6711b554-3a45-4ace-b541-9df8045d201f")
    );
    public static final FlaskBuff ROLL_COUNT = registerModded(COMBAT_ROLL, "roll_count",
            "combatroll:count",
            BUFF_CONFIG.rollCountValue(),
            FlaskBuffUtils.operationFromString(BUFF_CONFIG.rollCountOperation()),
            UUID.fromString("3205cc1f-d53b-46ff-863b-247f55bce180")
    );

    private static FlaskBuff register(String name, String attribute, double value,
                                      EntityAttributeModifier.Operation operation, UUID uuid) {

        FlaskBuff flaskBuff = createFlaskBuff(name, attribute, value, operation, uuid);

        addBuffItem(name);

        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), flaskBuff);

        return flaskBuff;
    }

    private static FlaskBuff registerModded(String requiredMod, String name, String attribute, double value,
                                            EntityAttributeModifier.Operation operation, UUID uuid) {
        if (!FabricLoader.getInstance().isModLoaded(requiredMod)) return null;

        return register(name, attribute, value, operation, uuid);
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
