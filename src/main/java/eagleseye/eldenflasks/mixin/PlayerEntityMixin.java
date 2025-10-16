package eagleseye.eldenflasks.mixin;

import eagleseye.eldenflasks.util.PlayerPersistentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerPersistentData {
    private NbtCompound buffData;

    @Override
    public NbtCompound getBuffData() {
        if (buffData == null) buffData = new NbtCompound();
        return buffData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeBuffData(NbtCompound nbt, CallbackInfo ci) {
        if (buffData != null) nbt.put("eldenflasks.buffs", buffData);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("eldenflasks.buffs")) {
            buffData = nbt.getCompound("eldenflasks.buffs");
        }
    }
}
