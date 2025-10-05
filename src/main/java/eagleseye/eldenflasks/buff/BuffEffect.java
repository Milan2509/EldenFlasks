package eagleseye.eldenflasks.buff;

import eagleseye.eldenflasks.util.PlayerPersistentData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

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

        //sanitize checks
        if (entity instanceof PlayerEntity player) {

            PlayerPersistentData persistent = (PlayerPersistentData) player;
            NbtCompound buffData = persistent.getBuffData();

            //buffs
            buff1 = BuffManager.getBuff(buffData.getString("buff1"));
            buff2 = BuffManager.getBuff(buffData.getString("buff2"));

            //buff 1 modifier
            if (buff1 != null) {
                //return if the player already has the modifier
                if (entity.getAttributes().getCustomInstance(buff1.getAttribute()).getModifier(buff1.getUuid()) != null)
                    return;

                //apply modifier
                entity.getAttributes().getCustomInstance(buff1.getAttribute()).addPersistentModifier(buff1.getModifier());
            }
            //buff 2 modifier
            if (buff2 != null) {
                //return if the player already has the modifier
                if (entity.getAttributes().getCustomInstance(buff2.getAttribute()).getModifier(buff2.getUuid()) != null)
                    return;

                //apply modifier
                entity.getAttributes().getCustomInstance(buff2.getAttribute()).addPersistentModifier(buff2.getModifier());
            }
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        if (entity.isPlayer() && buff1 != null) {
            entity.getAttributes().getCustomInstance(buff1.getAttribute()).removeModifier(buff1.getUuid());
        }

        if (entity.isPlayer() && buff2 != null) {
            entity.getAttributes().getCustomInstance(buff2.getAttribute()).removeModifier(buff2.getUuid());
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
