package eagleseye.eldenflasks.buff;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.text.Text;

import java.util.UUID;

public class FlaskBuff {
    private final EntityAttribute attribute;
    private final EntityAttributeModifier.Operation operation;
    private final UUID uuid;

    private final String name;
    private final String desc;


    private EntityAttributeModifier modifier;
    private final double value;

//    private final TieredPrefix prefix;
//
//    private enum TieredPrefix{
//        NONE,
//        TIER_1,
//        TIER_2,
//        TIER_3
//    }

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

        // Tier based name & description creation
//        TieredPrefix prefix;
//        if(name.contains("_1")) {
//            this.desc = name.replace("_1", "") + ".desc";
//            this.name = name.replace("_1", "");
//            this.prefix = TieredPrefix.TIER_1;
//        }
//        else if(name.contains("_2")) {
//            this.desc = name.replace("_2", "") + ".desc";
//            this.name = name.replace("_2", "");
//            this.prefix = TieredPrefix.TIER_2;
//        }
//        else if(name.contains("_3")) {
//            this.desc = name.replace("_3", "") + ".desc";
//            this.name = name.replace("_3", "");
//            this.prefix = TieredPrefix.TIER_3;
//        }
//        else {
//            this.desc = name + ".desc";
//            this.name = name;
//            this.prefix = TieredPrefix.NONE;
//        }
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

//    public double getIncrementAmount() {
//        return incrementAmount;
//    }

    public void setModifier(EntityAttributeModifier modifier){
        this.modifier = modifier;
    }
}
