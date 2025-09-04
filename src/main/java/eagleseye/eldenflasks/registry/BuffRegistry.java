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
            "buff.eldenflasks.speed_boost",
            "buff.eldenflasks.speed_boost.desc",
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            0.2,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.fromString("cfe365bb-a88d-4bb9-9d54-8a4fca93876c")
        )
    );

    private static FlaskBuff register(String name, FlaskBuff buff){
        BuffManager.registerBuff(new Identifier(EldenFlasks.MOD_ID, name), buff);
        return buff;
    }

    public static void init(){}
}
