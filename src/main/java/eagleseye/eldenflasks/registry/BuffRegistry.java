package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class BuffRegistry {
    public static final FlaskBuff SPEED_BUFF = new FlaskBuff(
            "buff.elden_flasks.speed_boost",
            "buff.elden_flasks.speed_boost.desc",
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            0.2,
            EntityAttributeModifier.Operation.ADDITION,
            UUID.randomUUID()
    );

    public static FlaskBuff register(Identifier id, FlaskBuff buff){

//        return Registry.register(BuffManager.FLASK_BUFF_KEY, id, buff);
        return SPEED_BUFF;

    }
}
