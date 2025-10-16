package eagleseye.eldenflasks.buff;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class FlaskBuff {
    private final EntityAttribute attribute;
    private final EntityAttributeModifier.Operation operation;
    private final UUID uuid;

    private final String name;
    private final String desc;

    private final EntityAttributeModifier modifier;
    private final double value;


    public FlaskBuff(String name, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, UUID uuid) {
        this.attribute = attribute;
        this.operation = operation;
        this.uuid = uuid;
        this.value = value;

        this.modifier = new EntityAttributeModifier(
                uuid,
                name,
                value,
                operation);

        this.name = name;
        this.desc = name + ".desc";
    }

    public EntityAttribute getAttribute() {
        return this.attribute;
    }

    public EntityAttributeModifier.Operation getOperation() {
        return this.operation;
    }

    public double getValue() {
        return this.value;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public EntityAttributeModifier getModifier() {
        return this.modifier;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }
}
