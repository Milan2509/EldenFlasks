package eagleseye.eldenflasks.item;

import eagleseye.eldenflasks.EldenFlasks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MixingFlaskItem extends Item {
    private final NbtCompound BUFF_DATA_SLOT_1 = new NbtCompound();

    public MixingFlaskItem(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt()) {
            NbtCompound nbt = stack.getOrCreateNbt();

            nbt.putInt("charges", 1);
            nbt.putInt("maxCharges", 1);
            nbt.putInt("duration", 30);
            nbt.putString("slot1", "empty");
            nbt.putString("slot2", "empty");
        }
        // How do I do this correctly???
//        else if (entity.isPlayer()){
//            entity.writeNbt(BUFF_DATA_SLOT_1);
//        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        NbtCompound nbt = stack.getNbt();
        nbt.putInt("charges", nbt.getInt("charges") - 1);

        user.addStatusEffect(new StatusEffectInstance(EldenFlasks.BUFFED_EFFECT,
                nbt.getInt("duration") * 20, 0, true, true));
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PlayerInventory playerInventory = user.getInventory();

        if(user.getStackInHand(hand).getNbt().getInt("charges") > 0){
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

//    @Override
//    public ActionResult useOnBlock(ItemUsageContext context) {
//        if(!context.getWorld().isClient){
//            BlockPos clickedPos = context.getBlockPos();
//            BlockState state = context.getWorld().getBlockState(clickedPos);
//            PlayerEntity player = context.getPlayer();
//            Hand hand = player.getActiveHand();
//            ItemStack stack = player.getStackInHand(hand);
//
//            if(stack.getNbt().getInt("charges") < stack.getNbt().getInt("maxCharges")
//                    && isRechargeBlock(state)){
//
//                int maxCharges = stack.getNbt().getInt("maxCharges");
//                NbtCompound nbt = stack.getOrCreateNbt();
//                nbt.putInt("charges", maxCharges);
//
//                context.getWorld().playSound(null, clickedPos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
//                        SoundCategory.BLOCKS, 1f, 1f);
//                player.sendMessage(Text.literal("Flask Recharged").formatted(Formatting.GOLD), true);
//
//                return ActionResult.SUCCESS;
//            }
//        }
//
//        return ActionResult.PASS;
//    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.eldenflasks.mixing_flask.desc").formatted(Formatting.DARK_GRAY));
        if (stack.hasNbt()) {
            int maxCharges = stack.getNbt().getInt("maxCharges");
            int charges = stack.getNbt().getInt("charges");
            int duration = stack.getNbt().getInt("duration");
            String slot1 = stack.getNbt().getString("slot1");
            String slot2 = stack.getNbt().getString("slot2");

            //Charges
            if (charges == maxCharges) {
                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GOLD));
            } else if (charges < maxCharges && charges > 0) {
                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.literal("Flask Empty").formatted(Formatting.RED));
            }
            //Duration
            tooltip.add(Text.literal("Duration: " + duration).formatted(Formatting.GRAY));
            //Slots
            tooltip.add(Text.literal("Slot 1: " + tooltipTranslationHelper(slot1)).formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Slot 2: " + tooltipTranslationHelper(slot2)).formatted(Formatting.AQUA));
        }
        //Fallback
        else {
            tooltip.add(Text.literal("Charges: 1/1").formatted(Formatting.GOLD));
            tooltip.add(Text.literal("Duration: 30 Sec").formatted(Formatting.GRAY));
            //Slots
            tooltip.add(Text.literal("Slot 1: Empty").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Slot 2: Empty").formatted(Formatting.AQUA));
        }
    }

    private String tooltipTranslationHelper(String key){
        if(key == "empty") return "Empty";

        return "ERROR";
    }
}
