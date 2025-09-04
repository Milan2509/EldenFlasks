package eagleseye.eldenflasks.util;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class EffectUtils {
    public void applyFlaskBuffEffect(LivingEntity entity, String buff1, String buff2, int duration){
        entity.addStatusEffect(new StatusEffectInstance(EldenFlasks.BUFFED_EFFECT, duration, 0, false, true));
    }
}
