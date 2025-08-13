package eagleseye.eldenflasks.item;

import eagleseye.eldenflasks.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static eagleseye.eldenflasks.EldenFlasks.FLASKS_CONFIG;

public class HealingFlaskItem extends Item {
//    public static final TagKey<Block> RECHARGE_BLOCKS = TagKey.of(RegistryKeys.BLOCK,
//            new Identifier(EldenFlasks.MOD_ID, "recharge_blocks"));

    public HealingFlaskItem(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt()) {
            NbtCompound nbt = stack.getOrCreateNbt();

            nbt.putInt("charges", FLASKS_CONFIG.maxCharges());
            nbt.putInt("maxCharges", FLASKS_CONFIG.maxCharges());
            nbt.putInt("drinkTime", FLASKS_CONFIG.drinkTime());
            nbt.putFloat("healing", FLASKS_CONFIG.healing());
        } else {
            resetFlaskWhenOverEnhanced(stack);
        }
    }

    private void resetFlaskWhenOverEnhanced(ItemStack stack){
        NbtCompound nbt = stack.getNbt();

        boolean charges = nbt.getInt("maxCharges") > FLASKS_CONFIG.maxChargeLimit();
        boolean healing = nbt.getFloat("healing") > FLASKS_CONFIG.healingLimit();
        boolean drinkTime = nbt.getInt("drinkTime") < FLASKS_CONFIG.drinkTimeLimit();

        if (charges) {
            nbt.putInt("maxCharges", FLASKS_CONFIG.maxChargeLimit());
            nbt.putInt("charges", FLASKS_CONFIG.maxChargeLimit());
        }
        if (healing) nbt.putFloat("healing", FLASKS_CONFIG.healingLimit());
        if (drinkTime) nbt.putInt("drinkTime", FLASKS_CONFIG.drinkTimeLimit());
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        NbtCompound nbt = stack.getNbt();
        nbt.putInt("charges", nbt.getInt("charges") - 1);

        user.heal(stack.getNbt().getFloat("healing"));
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PlayerInventory playerInventory = user.getInventory();

        if(playerInventory.count(ItemRegistry.HEALTH_FLASK) > FLASKS_CONFIG.maxHeldHealingFlasks()){
            user.applyDamageEffects(user, user);
            user.sendMessage(Text.literal("The Power of Too Many Flasks is Too Strong For You").formatted(Formatting.DARK_RED), true);
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        if(user.getStackInHand(hand).getNbt().getInt("charges") > 0){
            float testMath = (float) user.getStackInHand(hand).getNbt().getInt("charges") / user.getStackInHand(hand).getNbt().getInt("maxCharges");
            user.sendMessage(Text.literal(Float.toString(testMath)).formatted(Formatting.DARK_RED), false);
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient){
            BlockPos clickedPos = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(clickedPos);
            PlayerEntity player = context.getPlayer();
            Hand hand = player.getActiveHand();
            ItemStack stack = player.getStackInHand(hand);

            if(stack.getNbt().getInt("charges") < stack.getNbt().getInt("maxCharges")
                    && isRechargeBlock(state)){

                int maxCharges = stack.getNbt().getInt("maxCharges");
                NbtCompound nbt = stack.getOrCreateNbt();
                nbt.putInt("charges", maxCharges);

                context.getWorld().playSound(null, clickedPos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                        SoundCategory.BLOCKS, 1f, 1f);
                player.sendMessage(Text.literal("Flask Recharged").formatted(Formatting.GOLD), true);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return stack.getNbt().getInt("drinkTime");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Heals you when consumed, can be recharged at a Campfire.").formatted(Formatting.DARK_GRAY));
        if(stack.hasNbt()) {
            int maxCharges = stack.getNbt().getInt("maxCharges");
            int charges = stack.getNbt().getInt("charges");
            int drinkSpeed = stack.getNbt().getInt("drinkTime");
            float healing = stack.getNbt().getFloat("healing");

            if (charges == maxCharges) {
                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GOLD));
            } else if (charges < maxCharges && charges > 0) {
                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.literal("Flask Empty").formatted(Formatting.RED));
            }
            tooltip.add(Text.literal("Healing: " + (int) healing + " HP").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("Drink Speed: " + (float) drinkSpeed / 20 + " Sec").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Charges: " + FLASKS_CONFIG.maxCharges() + "/" + FLASKS_CONFIG.maxCharges()).formatted(Formatting.GOLD));
            tooltip.add(Text.literal("Healing: " + (int) FLASKS_CONFIG.healing() + " HP").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("Drink Speed: " + (float) FLASKS_CONFIG.drinkTime() / 20 + " Sec").formatted(Formatting.GRAY));
        }
    }

    private static boolean isRechargeBlock(BlockState state){
        return state.isOf(Blocks.CAMPFIRE) || state.isOf(Blocks.SOUL_CAMPFIRE);
    }
}
