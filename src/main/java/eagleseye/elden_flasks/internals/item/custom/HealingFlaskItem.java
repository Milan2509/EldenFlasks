package eagleseye.elden_flasks.internals.item.custom;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.component.EldenFlaskComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.tooltip.TooltipType;
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
        int currentUses = getCurrentUses(stack);

        if (currentUses <= 0 ) {
            user.sendMessage(Text.translatable("message.elden_flasks.no_uses_left").formatted(Formatting.RED));
            return stack;
        }

        user.heal(getHealAmount(stack));
        stack.set(EldenFlaskComponents.FLASK_CURRENT_USES, currentUses - 1);

        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Uses: " + getCurrentUses(stack) + " / " + getMaxUses(stack)));
        tooltip.add(Text.literal("Healing: " + (int)getHealAmount(stack)));

        super.appendTooltip(stack, context, tooltip, type);
    }

    private float getHealAmount(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.HEAL_AMOUNT) != null){
            return stack.get(EldenFlaskComponents.HEAL_AMOUNT);
        } else {
            return 0;
        }
    }

    private int getMaxUses(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.FLASK_MAX_USES) != null){
            return stack.get(EldenFlaskComponents.FLASK_MAX_USES);
        } else {
            return 0;
        }
    }

    private int getCurrentUses(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.FLASK_CURRENT_USES) != null){
            return stack.get(EldenFlaskComponents.FLASK_CURRENT_USES);
        } else {
            return 0;
        }
    }
}
