package eagleseye.eldenflasks.buff;

import jdk.dynalink.Operation;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.text.Text;

public class BuffObj {
    private EntityAttribute attribute;
    private EntityAttributeModifier.Operation operation;

    private Text name;
    private Text desc;

    public BuffObj(String name, String desc, EntityAttribute attribute, EntityAttributeModifier.Operation operation){
        this.attribute = attribute;
        this.operation = operation;

        this.name = Text.literal(name);
        this.desc = Text.literal(desc);
    }
}
