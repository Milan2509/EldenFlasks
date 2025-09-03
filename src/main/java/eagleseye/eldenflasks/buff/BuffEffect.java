package eagleseye.eldenflasks.buff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class BuffEffect extends StatusEffect {
    private EntityAttribute tmpAttribute;
    private UUID tmpUuid;

    public BuffEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x26B0FF);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        /// SOME GET IDENTIFIER FROM PLAYER DATA OR SOMETHING

        //sanitize checks NOTE: BUFF EXISTS CHECK DOES NOT WORK
        if(!entity.isPlayer() && !BuffManager.buffExists("elden_flasks:speed")) return;

        //buff
        FlaskBuff buff1 = BuffManager.getBuff("eldenflasks:speed");
        FlaskBuff buff2 = BuffManager.getBuff("eldenflasks:speed");
//
//        EntityAttributeModifier modifier = buff.getModifier();
//        this.tmpAttribute = buff.getAttribute();
//        this.tmpUuid = buff.getUuid();

        //return if the player already has the modifier
        if(entity.getAttributes().getCustomInstance(buff1.getAttribute()).getModifier(buff1.getUuid()) != null) return;

        //apply modifier
        entity.getAttributes().getCustomInstance(buff1.getAttribute()).addPersistentModifier(buff1.getModifier());
//
//        EntityAttributeModifier modifier = new EntityAttributeModifier(
//                uuid,
//                "buff.elden_flasks.armor",
//                1,
//                EntityAttributeModifier.Operation.ADDITION);
//
//        //prevent crash when modifier already exists
//        if(entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).getModifier(uuid) != null){
//            entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(uuid);
//        }


//        entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(modifier);

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        if(entity.isPlayer() && tmpAttribute != null && tmpUuid != null) {
            //breaks when the player rejoins, due to the variables resetting
            entity.getAttributes().getCustomInstance(tmpAttribute).removeModifier(tmpUuid);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
