package eagleseye.eldenflasks.buff;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

import java.util.HashMap;
import java.util.Map;

public class BuffManager {
    public static final Map<String, BuffObj> BUFF_LIST = registerBuffsToMap();

    public static final BuffObj ATTACK_DAMAGE = new BuffObj("Attack Damage", "Increases the player's attack damage with 12%",
            EntityAttributes.GENERIC_ATTACK_DAMAGE, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static Map<String, BuffObj> registerBuffsToMap(){
        Map<String, BuffObj> tmpMap = new HashMap<>();

        tmpMap.put("attack_damage", ATTACK_DAMAGE);

        return tmpMap;
    }
}
