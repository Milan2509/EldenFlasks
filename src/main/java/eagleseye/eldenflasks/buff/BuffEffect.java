package eagleseye.eldenflasks.buff;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class BuffEffect extends StatusEffect {
    UUID uuid = UUID.fromString("93319975-b488-4d7b-91de-4d1262d6b58f");

    public BuffEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x98D985);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        //player check
        if(!entity.isPlayer()) return;

        EntityAttributeModifier modifier = new EntityAttributeModifier(
                uuid,
                "buff.elden_flasks.armor",
                1,
                EntityAttributeModifier.Operation.ADDITION);

        //prevent crash when modifier already exists
        if(entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).getModifier(uuid) != null){
            entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(uuid);
        }


        entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(modifier);

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        if(entity.isPlayer()) entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(uuid);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
