package eagleseye.eldenflasks.buff;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class FlaskBuff {
    private final EntityAttribute attribute;
    private final EntityAttributeModifier.Operation operation;
    private final double incrementAmount;
    private final UUID uuid;

    private final String name;
    private final String desc;

    private EntityAttributeModifier modifier;
    private double value;
    private int tier = 1;

    public FlaskBuff(String name, EntityAttribute attribute, double value, double incrementAmount, EntityAttributeModifier.Operation operation, UUID uuid) {
        this.attribute = attribute;
        this.operation = operation;
        this.incrementAmount = incrementAmount;
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

    public double getIncrementAmount() {
        return incrementAmount;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void incrementTier(){
        this.tier++;
    }

    public void setModifier(EntityAttributeModifier modifier){
        this.modifier = modifier;
    }
    public void setValueByTier(){
        this.value = tier * incrementAmount;
    }
}
