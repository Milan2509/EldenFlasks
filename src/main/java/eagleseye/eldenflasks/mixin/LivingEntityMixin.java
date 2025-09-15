package eagleseye.eldenflasks.mixin;

import eagleseye.eldenflasks.item.HealingFlaskItem;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static eagleseye.eldenflasks.EldenFlasks.FLASKS_CONFIG;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract EntityGroup getGroup();
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        Entity killer = damageSource.getAttacker();

        LivingEntity entity = (LivingEntity) (Object) this;

        EntityType<?> entityType = entity.getType();
        Identifier entityId = Registries.ENTITY_TYPE.getId(entityType);

        if(FLASKS_CONFIG.entityKillBlacklist().contains(entityId.toString())) return;

        if (killer instanceof PlayerEntity player) {
            for (ItemStack stack : player.getInventory().main) {
                if (stack.getItem() instanceof HealingFlaskItem) {
                    NbtCompound nbt = stack.getOrCreateNbt();
                    int kills = nbt.getInt("kills");

                    if(FLASKS_CONFIG.fullyRechargeEntities().contains(entityId.toString())){
                        nbt.putString("killType", "full");
                        nbt.putInt("kills", kills + nbt.getInt("killRequirement"));
                    } else{
                        nbt.putString("killType", "basic");
                        nbt.putInt("kills", kills + 1);
                    }
                }
            }

        }
    }
}