package eagleseye.eldenflasks.item;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.util.FlaskBuffUtils;
import eagleseye.eldenflasks.util.PlayerPersistentData;
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
    public MixingFlaskItem(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!stack.hasNbt()) {
            NbtCompound nbt = stack.getOrCreateNbt();

            nbt.putInt("charges", 1);
            nbt.putInt("maxCharges", 1);
            nbt.putInt("duration", 60);
            nbt.putString("slot1", "empty");
            nbt.putString("slot2", "eldenflasks:roll_count");
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            NbtCompound nbt = stack.getNbt();
            nbt.putInt("charges", nbt.getInt("charges") - 1);

            PlayerPersistentData persistent = (PlayerPersistentData) player;
            NbtCompound buffData = persistent.getBuffData();

            buffData.putString("buff1", nbt.getString("slot1"));
            buffData.putString("buff2", nbt.getString("slot2"));

            user.addStatusEffect(new StatusEffectInstance(EldenFlasks.BUFFED_EFFECT,
                    nbt.getInt("duration") * 20, 0, true, true));

        }
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PlayerInventory playerInventory = user.getInventory();

        if (user.getStackInHand(hand).getNbt().getInt("charges") > 0) {
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

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

//            Charges
//            if (charges == maxCharges) {
//                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GOLD));
//            } else if (charges < maxCharges && charges > 0) {
//                tooltip.add(Text.literal("Charges: " + charges + "/" + maxCharges).formatted(Formatting.GRAY));
//            } else {
//                tooltip.add(Text.literal("Flask Empty").formatted(Formatting.RED));
//            }
            //Duration
            tooltip.add(Text.literal("Duration: " + duration).formatted(Formatting.GRAY));
            //Slots
            if (BuffManager.getBuff(slot1) != null) {
                tooltip.add(Text.literal("Slot 1: ").formatted(Formatting.AQUA).append(Text.translatable(BuffManager.getBuff(slot1).getName())));
                tooltip.add(FlaskBuffUtils.createBuffDescription(BuffManager.getBuff(slot1)));
            } else if (slot1 == "empty"){
                tooltip.add(Text.literal("Slot 1: Empty").formatted(Formatting.AQUA));
            }
            else {
                tooltip.add(Text.translatable("buff.invalid.desc", "buff not found"));
            }

            if (BuffManager.getBuff(slot2) != null) {
                tooltip.add(Text.literal("Slot 2: ").formatted(Formatting.AQUA).append(Text.translatable(BuffManager.getBuff(slot2).getName())));
                tooltip.add(FlaskBuffUtils.createBuffDescription(BuffManager.getBuff(slot2)));
            } else if (slot1 == "empty"){
                tooltip.add(Text.literal("Slot 2: Empty").formatted(Formatting.AQUA));
            }
            else {
                tooltip.add(Text.translatable("buff.invalid.desc", "buff not found"));
            }
        }
        //Fallback
        else {
//            tooltip.add(Text.literal("Charges: 1/1").formatted(Formatting.GOLD));
            tooltip.add(Text.literal("Duration: 30 Sec").formatted(Formatting.GRAY));
            //Slots
            tooltip.add(Text.literal("Slot 1: Empty").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Slot 2: Empty").formatted(Formatting.AQUA));
        }
    }
}
