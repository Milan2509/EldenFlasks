package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class BuffRegistry {
    public static final FlaskBuff SPEED = register("speed", new FlaskBuff(
            "buff.eldenflasks.speed",
            "buff.eldenflasks.speed.desc",
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            0.1,
            EntityAttributeModifier.Operation.MULTIPLY_BASE,
            UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c")
        )
    );

    public static final FlaskBuff STRENGTH = register("strength", new FlaskBuff(
                    "buff.eldenflasks.strength",
                    "buff.eldenflasks.strength.desc",
                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    0.12,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE,
                    UUID.fromString("52e6ada6-588d-4457-b539-3628a605a94e")
            )
    );

    private static FlaskBuff register(String name, FlaskBuff buff){
        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    public static void init(){}
}
