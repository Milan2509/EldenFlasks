package eagleseye.elden_flasks.internals.item.custom;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.component.ComponentUtil;
import eagleseye.elden_flasks.internals.component.EldenFlaskComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class HealingFlaskItem extends Item {
    public HealingFlaskItem() {
        super(new Item.Settings()
                .rarity(Rarity.RARE)
                .maxCount(1)
                .component(EldenFlaskComponents.FLASK_MAX_USES, EldenFlasks.flasksConfig.healing_flask.base_uses_amount)
                .component(EldenFlaskComponents.FLASK_CURRENT_USES, EldenFlasks.flasksConfig.healing_flask.base_uses_amount)
                .component(EldenFlaskComponents.HEAL_AMOUNT, EldenFlasks.flasksConfig.healing_flask.base_healing_amount)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int currentUses = ComponentUtil.getCurrentUses(stack);

        if (currentUses <= 0 ) {
            user.sendMessage(Text.translatable("message.elden_flasks.no_uses_left").formatted(Formatting.RED));
            return stack;
        }

        user.heal(ComponentUtil.getHealAmount(stack));
        stack.set(EldenFlaskComponents.FLASK_CURRENT_USES, currentUses - 1);
        sendHealingVisuals(user);

        return super.finishUsing(stack, world, user);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!isRechargeBlock(Registries.BLOCK.getId(context.getWorld().getBlockState(context.getBlockPos()).getBlock()).toString())) {
            return ActionResult.PASS;
        }

        context.getStack().set(EldenFlaskComponents.FLASK_CURRENT_USES, ComponentUtil.getMaxUses(context.getStack()));
        sendRechargeVisuals(context.getPlayer());

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        MutableText usesText = Text.literal("Uses: " + ComponentUtil.getCurrentUses(stack) + "/" + ComponentUtil.getMaxUses(stack));
        if(ComponentUtil.getCurrentUses(stack) <= 0){
            tooltip.add(usesText.formatted(Formatting.RED));
        } else {
            tooltip.add(usesText.formatted(Formatting.GOLD));
        }
        tooltip.add(Text.literal("Healing: " + (int) ComponentUtil.getHealAmount(stack)  + " HP").formatted(Formatting.GOLD));

        super.appendTooltip(stack, context, tooltip, type);
    }

    private boolean isRechargeBlock(String blockId){
        return EldenFlasks.flasksConfig.healing_flask.recharge_blocks.contains(blockId);
    }

    private void sendHealingVisuals(LivingEntity user){
        if(user instanceof PlayerEntity player){
//            player.playSound(SoundEvents);
        }
    }

    private void sendRechargeVisuals(LivingEntity user){
        if(user instanceof PlayerEntity player){
//            player.playSound(SoundEvents);
        }
    }
}
