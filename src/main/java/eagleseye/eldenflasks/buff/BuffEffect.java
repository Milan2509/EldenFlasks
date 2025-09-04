package eagleseye.eldenflasks.buff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class BuffEffect extends StatusEffect {
    private FlaskBuff buff1;
    private FlaskBuff buff2;

    public BuffEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x26B0FF);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        /// SOME GET IDENTIFIER FROM PLAYER DATA OR SOMETHING

        //sanitize checks
        if(!entity.isPlayer() && !BuffManager.buffExists("eldenflasks:test")) return;

        //buffs
        buff1 = BuffManager.getBuff("eldenflasks:speed");
        buff2 = BuffManager.getBuff("eldenflasks:speed");

        //return if the player already has the modifier
        if(entity.getAttributes().getCustomInstance(buff1.getAttribute()).getModifier(buff1.getUuid()) != null) return;

        //apply modifier
        entity.getAttributes().getCustomInstance(buff1.getAttribute()).addPersistentModifier(buff1.getModifier());
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        if(entity.isPlayer() && buff1 != null) {
            entity.getAttributes().getCustomInstance(buff1.getAttribute()).removeModifier(buff1.getUuid());
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
