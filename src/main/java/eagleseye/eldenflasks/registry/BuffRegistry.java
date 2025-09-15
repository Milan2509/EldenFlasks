package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class BuffRegistry {
    public static final FlaskBuff SPEED_t1 = register("speed", new FlaskBuff(
            "buff.eldenflasks.speed",
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            0.05,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c")
        )
    );

    public static final FlaskBuff STRENGTH_t1 = register("strength", new FlaskBuff(
                    "buff.eldenflasks.strength",
                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    0.05,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("52e6ada6-588d-4457-b539-3628a605a94e")
            )
    );

    public static final FlaskBuff ARMOR_t1 = register("armor", new FlaskBuff(
                    "buff.eldenflasks.armor",
                    EntityAttributes.GENERIC_ARMOR,
                    2,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("138932d2-9959-4c9f-b5da-f581e499324f")
            )
    );

    public static final FlaskBuff TOUGHNESS_t1 = register("toughness", new FlaskBuff(
                    "buff.eldenflasks.toughness",
                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                    1,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("1391114c-ec2a-46d1-b626-467323cac761")
            )
    );

    public static final FlaskBuff MAX_HEALTH_t1 = register("max_health", new FlaskBuff(
                    "buff.eldenflasks.max_health",
                    EntityAttributes.GENERIC_MAX_HEALTH,
                    4,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("a40afabb-35a2-40b6-8322-adce9901f1c3")
            )
    );

    public static final FlaskBuff ATTACK_SPEED_t1 = register("attack_speed", new FlaskBuff(
                    "buff.eldenflasks.attack_speed",
                    EntityAttributes.GENERIC_ATTACK_SPEED,
                    0.02,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("6270e2e6-45e5-4b23-be87-21b788572d47")
            )
    );

    public static final FlaskBuff LUCK_t1 = register("luck", new FlaskBuff(
                    "buff.eldenflasks.luck",
                    EntityAttributes.GENERIC_LUCK,
                    2,
                    EntityAttributeModifier.Operation.ADDITION,
                    UUID.fromString("a57a00b6-09d2-4099-98e9-4f684f0c3457")
            )
    );

    public static final FlaskBuff KNOCKBACK_RES_t1 = register("knockback_res", new FlaskBuff(
                    "buff.eldenflasks.knockback_res",
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    0.1,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("9663d9d8-49a0-41f2-8688-8721e30accc3")
            )
    );

    // MODDED
    private static final String SPELL_POWER = "spell_power";
    private static final String ETERNAL_ATTRIBUTES = "eternal_attributes";

    // Spell Power
    public static final FlaskBuff FIRE_POWER_t1 = registerModded(SPELL_POWER, "fire_power", new FlaskBuff(
                    "buff.eldenflasks.fire_power",
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    0.1,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("ec3edbe8-7fe2-48ef-b9e5-b54ca30f2500")
            )
    );

    private static FlaskBuff register(String name, FlaskBuff buff){
        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    private static FlaskBuff registerModded(String modId, String name, FlaskBuff buff){
        if (!FabricLoader.getInstance().isModLoaded(modId)) return null;

        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    public static void init(){}
}
