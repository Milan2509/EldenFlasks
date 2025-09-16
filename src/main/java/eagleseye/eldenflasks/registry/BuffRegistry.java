package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import eagleseye.eldenflasks.item.FlaskBuffItem;
import elocindev.eternal_attributes.registry.AttributeRegistry;
import net.fabric_extras.ranged_weapon.api.EntityAttributes_RangedWeapon;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class BuffRegistry {
    public static final FlaskBuff MOVEMENT_SPEED = register("movement_speed", new FlaskBuff(
                    "buff.eldenflasks.movement_speed",
                    EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c")
            )
    );

    public static final FlaskBuff ATTACK_DAMAGE = register("attack_damage", new FlaskBuff(
                    "buff.eldenflasks.attack_damage",
                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("52e6ada6-588d-4457-b539-3628a605a94e")
            )
    );

    public static final FlaskBuff ARMOR = register("armor", new FlaskBuff(
                    "buff.eldenflasks.armor",
                    EntityAttributes.GENERIC_ARMOR,
                    2,
                    2,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("138932d2-9959-4c9f-b5da-f581e499324f")
            )
    );

    public static final FlaskBuff TOUGHNESS = register("toughness", new FlaskBuff(
                    "buff.eldenflasks.toughness",
                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                    1,
                    1,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("1391114c-ec2a-46d1-b626-467323cac761")
            )
    );

    public static final FlaskBuff MAX_HEALTH = register("max_health", new FlaskBuff(
                    "buff.eldenflasks.max_health",
                    EntityAttributes.GENERIC_MAX_HEALTH,
                    2,
                    2,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("a40afabb-35a2-40b6-8322-adce9901f1c3")
            )
    );

    public static final FlaskBuff ATTACK_SPEED = register("attack_speed", new FlaskBuff(
                    "buff.eldenflasks.attack_speed",
                    EntityAttributes.GENERIC_ATTACK_SPEED,
                    0.02,
                    0.02,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("6270e2e6-45e5-4b23-be87-21b788572d47")
            )
    );

    public static final FlaskBuff LUCK = register("luck", new FlaskBuff(
                    "buff.eldenflasks.luck",
                    EntityAttributes.GENERIC_LUCK,
                    2,
                    1,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("a57a00b6-09d2-4099-98e9-4f684f0c3457")
            )
    );

    public static final FlaskBuff KNOCKBACK_RES = register("knockback_res", new FlaskBuff(
                    "buff.eldenflasks.knockback_res",
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    0.1,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("9663d9d8-49a0-41f2-8688-8721e30accc3")
            )
    );

    // MODDED
    private static final String SPELL_POWER = "spell_power";
    private static final String ETERNAL_ATTRIBUTES = "eternal_attributes";
    private static final String RANGED_WEAPON = "ranged_weapon";

    // Spell Power
    public static final FlaskBuff FIRE = registerModded(SPELL_POWER, "fire_power", new FlaskBuff(
                    "buff.eldenflasks.fire_power",
                    Registries.ATTRIBUTE.get(new Identifier("spell_power:fire")),
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("ec3edbe8-7fe2-48ef-b9e5-b54ca30f2500")
            )
    );
    public static final FlaskBuff FROST = registerModded(SPELL_POWER, "frost_power", new FlaskBuff(
                    "buff.eldenflasks.frost_power",
                    Registries.ATTRIBUTE.get(new Identifier("spell_power:frost")),
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("f6ff3755-e55a-4fe5-9c0c-02245c663725")
            )
    );
    public static final FlaskBuff ARCANE = registerModded(SPELL_POWER, "frost_power", new FlaskBuff(
                    "buff.eldenflasks.frost_power",
                    Registries.ATTRIBUTE.get(new Identifier("spell_power:arcane")),
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("0aefec74-21e7-4448-8358-037e8b9c5cdd")
            )
    );
    public static final FlaskBuff HEALING = registerModded(SPELL_POWER, "healing_power", new FlaskBuff(
                    "buff.eldenflasks.healing_power",
                    Registries.ATTRIBUTE.get(new Identifier("spell_power:healing")),
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("fc37458d-e073-4104-8d63-cb909773beba")
            )
    );
    // Eternal Attributes
    public static final FlaskBuff UNHOLY = registerModded(ETERNAL_ATTRIBUTES, "unholy_power", new FlaskBuff(
                    "buff.eldenflasks.unholy_power",
                    AttributeRegistry.UNHOLY_POWER,
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("abcd7c12-1c0c-4f84-a01a-7ecebaada56c")
            )
    );
    public static final FlaskBuff BLOOD = registerModded(ETERNAL_ATTRIBUTES, "blood_power", new FlaskBuff(
                    "buff.eldenflasks.blood_power",
                    AttributeRegistry.BLOOD_POWER,
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("f502d5e0-5071-44ba-8e9c-fa006ff5fe30")
            )
    );
    // Ranged Weapon
    public static final FlaskBuff RANGED_DAMAGE = registerModded(RANGED_WEAPON, "ranged_damage", new FlaskBuff(
                    "buff.eldenflasks.ranged_damage",
                    EntityAttributes_RangedWeapon.DAMAGE.attribute,
                    0.05,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("88bde36d-2520-451f-a55d-0207e54847a1")
            )
    );
    public static final FlaskBuff DRAW_SPEED = registerModded(RANGED_WEAPON, "draw_speed", new FlaskBuff(
                    "buff.eldenflasks.draw_speed",
                    EntityAttributes_RangedWeapon.HASTE.attribute,
                    0.02,
                    0.02,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("97200f22-94b8-4bd2-aaf9-01d44b10cde9")
            )
    );

    private static FlaskBuff register(String name, FlaskBuff buff) {
        //TODO: Create system where 3 tiers of the buff are made based on incrementAmount (remove from FlaskBuff)

        Item buffItem = Registry.register(Registries.ITEM, Identifier.of(EldenFlasks.MOD_ID, "orb_" + name), new FlaskBuffItem(new FabricItemSettings(),
                EldenFlasks.MOD_ID + ":" + name));

        FlaskGroup.addBuffItem(buffItem);

        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    private static FlaskBuff registerModded(String modId, String name, FlaskBuff buff) {
        if (!FabricLoader.getInstance().isModLoaded(modId)) return null;

        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    public static void init() {
    }
}
