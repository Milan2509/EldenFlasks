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
import net.minecraft.screen.PropertyDelegate;
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

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

//    private static boolean canMix = false;

    public FlaskMixerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLASK_MIXER_BLOCK_ENTITY, pos, state);
        // Handles mixing progress time
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FlaskMixerBlockEntity.this.progress;
                    case 1 -> FlaskMixerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FlaskMixerBlockEntity.this.progress = value;
                    case 1 -> FlaskMixerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
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
        nbt.putInt("flaskMixer.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("flaskMixer.progress");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FlaskMixerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;

        if (getStack(OUTPUT_SLOT).isEmpty()) {

            if (this.hasRecipe()) {
                this.increaseProgress();
                markDirty(world, pos, state);

                if (hasMixingFinished()) {
                    this.createFlask(world);
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
                markDirty(world, pos, state);
            }

        }

    }

    private void createFlask(World world) {
        ItemStack input = getStack(INPUT_SLOT);
        ItemStack addition = getStack(ADDITION_SLOT);
        ItemStack resultHealing = null;
        ItemStack resultMixing = null;

        if (input.getItem() instanceof HealingFlaskItem) resultHealing = healingFlaskResult(input, addition.getItem());
        if (input.getItem() instanceof MixingFlaskItem) resultMixing = mixingFlaskResult(input, addition.getItem());

        if(getValidFlask(input) == 1) this.setStack(OUTPUT_SLOT, resultHealing);
        if(getValidFlask(input) == 2) this.setStack(OUTPUT_SLOT, resultMixing);

        this.removeInputItems();

        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW,
                SoundCategory.BLOCKS, 1f, 1f);
    }

    private void removeInputItems(){
        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(ADDITION_SLOT, 1);
    }

    private ItemStack healingFlaskResult(ItemStack flask, Item addition) {
        ItemStack result = new ItemStack(ItemRegistry.HEALTH_FLASK);
        NbtCompound originalNbt = flask.getNbt();
        NbtCompound newNbt = result.getOrCreateNbt();

        int maxCharges = originalNbt.getInt("maxCharges");
        float healing = originalNbt.getFloat("healing");


        if (addition instanceof ChargeEnhancerItem) {
            maxCharges += EldenFlasks.FLASKS_CONFIG.healingFlaskStats.maxChargeModifier();
        } else if (addition instanceof HealingEnhancerItem) {
            healing += EldenFlasks.FLASKS_CONFIG.healingFlaskStats.healingModifier();
        }

        newNbt.putInt("charges", maxCharges);
        newNbt.putInt("maxCharges", maxCharges);
        newNbt.putFloat("healing", healing);

        return result;
    }

    private ItemStack mixingFlaskResult(ItemStack flask, Item addition) {
        ItemStack result = new ItemStack(ItemRegistry.MIXING_FLASK);
        NbtCompound originalNbt = flask.getNbt();
        NbtCompound newNbt = result.getOrCreateNbt();

        String slot1 = originalNbt.getString("slot1");
        String slot2 = originalNbt.getString("slot2");

        // Buff adding
        if(addition instanceof FlaskBuffItem){
            if(slot1 == "empty"){
                newNbt.putString("slot1", ((FlaskBuffItem) addition).getBuffId());
            }
            else if(slot2 == "empty"){
                newNbt.putString("slot2", ((FlaskBuffItem) addition).getBuffId());
            }
            else {
                result = flask;
            }
        }
//        else if (addition instanceof DurationEnhancerItem)
//        else if (addition instanceof FlaskBuffRemoverItem)

        return result;
    }

    private boolean hasRecipe() {
        ItemStack input = getStack(INPUT_SLOT);
        Item enhancerItem = getStack(ADDITION_SLOT).getItem();

        boolean hasRecipe = false;

        int chargeLimit = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.maxChargeLimit();
        float healingLimit = EldenFlasks.FLASKS_CONFIG.healingFlaskStats.healingLimit();

        if (input.hasNbt() && getValidFlask(input) == 1) {
            NbtCompound nbt = input.getNbt();
            int currentMaxCharges = nbt.getInt("maxCharges");
            float currentHealing = nbt.getFloat("healing");

            if (enhancerItem instanceof ChargeEnhancerItem && currentMaxCharges < chargeLimit) hasRecipe = true;
            if (enhancerItem instanceof HealingEnhancerItem && currentHealing < healingLimit) hasRecipe = true;
        }

        if (input.hasNbt() && getValidFlask(input) == 2) {
            NbtCompound nbt = input.getNbt();
            String slot1 = nbt.getString("slot1");
            String slot2 = nbt.getString("slot1");

            if (slot1 == "empty" || slot2 == "empty") hasRecipe = true;
            else return false;
        }

        return hasRecipe;
    }
    // Returns int based on if a valid flask is present
    // 0 -> no valid flask
    // 1 -> healing flask
    // 2 -> mixing flask
    private int getValidFlask(ItemStack input){
        if(input.getItem() == ItemRegistry.HEALTH_FLASK) return 1;
        else if (input.getItem() == ItemRegistry.MIXING_FLASK) return 2;
        else return 0;
    };

    private boolean hasMixingFinished() {
        return progress >= maxProgress;
    }

    private void increaseProgress() {
        progress++;
    }

    private void resetProgress() {
        progress = 0;
    }



//    public static void setCanMix(boolean canMix) {
//        FlaskMixerBlockEntity.canMix = canMix;
//    }


}
