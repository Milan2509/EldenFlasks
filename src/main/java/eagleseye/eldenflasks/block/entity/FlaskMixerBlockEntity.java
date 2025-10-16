package eagleseye.eldenflasks.block.entity;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.item.*;
import eagleseye.eldenflasks.registry.BlockEntityRegistry;
import eagleseye.eldenflasks.registry.ItemRegistry;
import eagleseye.eldenflasks.screen.FlaskMixerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FlaskMixerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int ADDITION_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private static boolean canMix = false;

    public FlaskMixerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLASK_MIXER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Flask Mixer");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FlaskMixerScreenHandler(syncId, playerInventory, this);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;

        if (getStack(OUTPUT_SLOT).isEmpty()) {
            if (!canMix) return;
            if (this.hasRecipe()) {
                this.createFlask(world);
            }
            setCanMix(false);
        }
    }

    private void createFlask(World world) {
        ItemStack input = getStack(INPUT_SLOT);
        ItemStack addition = getStack(ADDITION_SLOT);
        ItemStack result = null;

        if (input.getItem() instanceof HealingFlaskItem) result = healingFlaskResult(input, addition.getItem());
        if (input.getItem() instanceof MixingFlaskItem) result = healingFlaskResult(input, addition.getItem());

        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(ADDITION_SLOT, 1);
        this.setStack(OUTPUT_SLOT, result);

        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW,
                SoundCategory.BLOCKS, 1f, 1f);
    }

    private ItemStack healingFlaskResult(ItemStack flask, Item addition) {
        ItemStack result = new ItemStack(ItemRegistry.HEALTH_FLASK);
        NbtCompound originalNbt = flask.getNbt();
        NbtCompound newNbt = result.getOrCreateNbt();

        int maxCharges = originalNbt.getInt("maxCharges");
        float healing = originalNbt.getFloat("healing");


        if (addition instanceof ChargeEnhancerItem) {
            maxCharges += EldenFlasks.FLASKS_CONFIG.maxChargeModifier();
        } else if (addition instanceof HealingEnhancerItem) {
            healing += EldenFlasks.FLASKS_CONFIG.healingModifier();
        }

        newNbt.putInt("charges", maxCharges);
        newNbt.putInt("maxCharges", maxCharges);
        newNbt.putFloat("healing", healing);

        return result;
    }

    // WARNING!!
    // Do not remove the method below, for some reason this breaks stuff, I don't know why.
    // The mixing no longer functions at all.
    private ItemStack mixingFlaskResult(ItemStack flask, Item addition) {
        ItemStack result = new ItemStack(ItemRegistry.HEALTH_FLASK);
        NbtCompound originalNbt = flask.getNbt();
        NbtCompound newNbt = result.getOrCreateNbt();

        int maxCharges = originalNbt.getInt("maxCharges");
        float healing = originalNbt.getFloat("healing");


        if (addition instanceof ChargeEnhancerItem) {
            maxCharges += EldenFlasks.FLASKS_CONFIG.maxChargeModifier();
        } else if (addition instanceof HealingEnhancerItem) {
            healing += EldenFlasks.FLASKS_CONFIG.healingModifier();
        }

        newNbt.putInt("charges", maxCharges);
        newNbt.putInt("maxCharges", maxCharges);
        newNbt.putFloat("healing", healing);

        return result;
    }

    private boolean hasRecipe() {
        ItemStack input = getStack(INPUT_SLOT);
        Item enhancerItem = getStack(ADDITION_SLOT).getItem();

        boolean hasFlask = input.getItem() == ItemRegistry.HEALTH_FLASK;
        boolean hasValidEnhancer = false;

        int chargeLimit = EldenFlasks.FLASKS_CONFIG.maxChargeLimit();
        float healingLimit = EldenFlasks.FLASKS_CONFIG.healingLimit();

        if (input.hasNbt()) {
            NbtCompound nbt = input.getNbt();
            int currentMaxCharges = nbt.getInt("maxCharges");
            float currentHealing = nbt.getFloat("healing");

            if (enhancerItem instanceof ChargeEnhancerItem && currentMaxCharges < chargeLimit) hasValidEnhancer = true;
            if (enhancerItem instanceof HealingEnhancerItem && currentHealing < healingLimit) hasValidEnhancer = true;
        }

        return hasFlask && hasValidEnhancer;
    }

    public static void setCanMix(boolean canMix) {
        FlaskMixerBlockEntity.canMix = canMix;
    }


}
