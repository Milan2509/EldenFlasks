package eagleseye.elden_flasks.mixin;

import eagleseye.elden_flasks.internals.util.ComponentUtils;
import eagleseye.elden_flasks.internals.item.custom.HealingFlaskItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Shadow
    @Nullable
    private LivingEntity attacker;

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();

        if (!(attacker instanceof PlayerEntity player)) return;

        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof HealingFlaskItem) {
                ComponentUtils.incrementFlaskKillCount(stack);
            }
        }
    }
}
