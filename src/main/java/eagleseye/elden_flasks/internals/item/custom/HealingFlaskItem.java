package eagleseye.elden_flasks.internals.item.custom;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.util.ComponentUtils;
import eagleseye.elden_flasks.internals.component.EldenFlaskComponents;
import eagleseye.elden_flasks.internals.util.VisualsUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
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
                .component(EldenFlaskComponents.RECHARGE_KILL_CURRENT, 0)
                .component(EldenFlaskComponents.RECHARGE_KILL_REQUIREMENT, EldenFlasks.flasksConfig.healing_flask.recharge_kill_requirement)
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
        int currentUses = ComponentUtils.getCurrentUses(stack);

        if (currentUses <= 0) {
            if(user instanceof PlayerEntity player) {
                player.sendMessage(Text.translatable("message.elden_flasks.no_uses_left").formatted(Formatting.RED), true);
            }
            return stack;
        }

        user.heal(ComponentUtils.getHealAmount(stack));
        stack.set(EldenFlaskComponents.FLASK_CURRENT_USES, currentUses - 1);
        VisualsUtils.sendHealingVisuals(user);

        return super.finishUsing(stack, world, user);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!isRechargeBlock(Registries.BLOCK.getId(context.getWorld().getBlockState(context.getBlockPos()).getBlock()).toString())) {
            return ActionResult.PASS;
        }

        context.getStack().set(EldenFlaskComponents.FLASK_CURRENT_USES, ComponentUtils.getMaxUses(context.getStack()));
        VisualsUtils.sendRechargeVisuals(context.getPlayer());

        return ActionResult.PASS;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if(hasReachedKillRequirement(stack)) {
            ComponentUtils.setCurrentUses(stack, ComponentUtils.getCurrentUses(stack) + EldenFlasks.flasksConfig.healing_flask.recharge_from_kill_amount);
            ComponentUtils.setCurrentKillCount(stack, ComponentUtils.getCurrentKillCount(stack) - ComponentUtils.getRequiredKillCount(stack));
            if (entity instanceof PlayerEntity player) {
                VisualsUtils.sendRechargeVisuals(player);
            }
        }

        if(ComponentUtils.getCurrentUses(stack) > ComponentUtils.getMaxUses(stack)) {
            ComponentUtils.setCurrentUses(stack, ComponentUtils.getMaxUses(stack));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        MutableText usesText = Text.literal("Charges: " + ComponentUtils.getCurrentUses(stack) + "/" + ComponentUtils.getMaxUses(stack));
        if(ComponentUtils.getCurrentUses(stack) <= 0){
            tooltip.add(usesText.formatted(Formatting.RED));
        } else {
            tooltip.add(usesText.formatted(Formatting.GOLD));
        }
        tooltip.add(Text.literal("Healing: " + (int) ComponentUtils.getHealAmount(stack)  + " HP").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("Kills for Recharge: " + ComponentUtils.getCurrentKillCount(stack)  + "/" + ComponentUtils.getRequiredKillCount(stack)).formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);

        if(Screen.hasShiftDown()) {
            for(String rechargeBlock : EldenFlasks.flasksConfig.healing_flask.recharge_blocks) {
                Identifier blockId = Identifier.of(rechargeBlock);
                if(Registries.BLOCK.containsId(blockId)) {
                    tooltip.add(Text.translatable(Registries.BLOCK.get(blockId).getTranslationKey()).formatted(Formatting.DARK_GRAY));
                }
            }
        } else {
            tooltip.add(Text.translatable("tooltip.elden_flasks.healing_flask.recharge_blocks").formatted(Formatting.DARK_GRAY));
        }
    }

    private boolean isRechargeBlock(String blockId){
        return EldenFlasks.flasksConfig.healing_flask.recharge_blocks.contains(blockId);
    }

    private boolean hasReachedKillRequirement(ItemStack stack){
        return ComponentUtils.getCurrentKillCount(stack) >= ComponentUtils.getRequiredKillCount(stack);
    }
}
