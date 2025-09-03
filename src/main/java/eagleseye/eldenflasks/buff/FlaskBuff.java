package eagleseye.eldenflasks.buff;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class FlaskBuff {
    private final EntityAttribute attribute;
    private final EntityAttributeModifier.Operation operation;
    private final double value;
    private final UUID uuid;

    private final EntityAttributeModifier modifier;

    private final String name;
    private final String desc;

    /*
        Might create registry and stuff in the future, too complicated for me right now.
     */

    //Create codec for EntityAttributeModifier
//    public static final Codec<EntityAttributeModifier.Operation> OPERATION_CODEC =
//            Codec.STRING.xmap(
//                    EntityAttributeModifier.Operation::valueOf,
//                    EntityAttributeModifier.Operation::name
//            );
//
//    //Codec
//    public static final Codec<FlaskBuff> CODEC = RecordCodecBuilder.create(instance -> instance.group(
//            Codec.STRING.fieldOf("name").forGetter(FlaskBuff::getName),
//            Codec.STRING.fieldOf("desc").forGetter(FlaskBuff::getDesc),
//            Registries.ATTRIBUTE.getCodec().fieldOf("attribute").forGetter(FlaskBuff::getAttribute),
//            Codec.DOUBLE.fieldOf("value").forGetter(FlaskBuff::getValue),
//            OPERATION_CODEC.fieldOf("operation").forGetter(FlaskBuff::getOperation),
//            Uuids.CODEC.fieldOf("uuid").forGetter(FlaskBuff::getUuid)
//    ).apply(instance, FlaskBuff::new));

    public FlaskBuff(String name, String desc,
                     EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, UUID uuid){
        this.attribute = attribute;
        this.operation = operation;
        this.uuid = uuid;
        this.value = value;

        this.modifier = new EntityAttributeModifier(
                uuid,
                name,
                value,
                EntityAttributeModifier.Operation.ADDITION);

        this.name = name;
        this.desc = desc;
    }

    public EntityAttribute getAttribute(){
        return this.attribute;
    }

    public EntityAttributeModifier.Operation getOperation(){
        return this.operation;
    }

    public double getValue(){
        return this.value;
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public EntityAttributeModifier getModifier(){
        return this.modifier;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }
}
