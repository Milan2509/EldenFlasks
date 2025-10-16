package eagleseye.eldenflasks.mixin;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.item.HealingFlaskItem;
import eagleseye.eldenflasks.util.PlayerPersistentData;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerPersistentData {
    private NbtCompound buffData;

    @Override
    public NbtCompound getBuffData() {
        if(buffData == null) buffData = new NbtCompound();
        return buffData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeBuffData(NbtCompound nbt, CallbackInfo ci){
        if(buffData != null) nbt.put("eldenflasks.buffs", buffData);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("eldenflasks.buffs")) {
            buffData = nbt.getCompound("eldenflasks.buffs");
        }
    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("RETURN"), cancellable = true)
    private void keepHealingFlaskInInventory(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir){
        if(EldenFlasks.FLASKS_CONFIG.keepHealingFlaskOnDeath()){
            if(stack.getItem() instanceof HealingFlaskItem flask){
                cir.setReturnValue(null);
            }
        }
    }
}
