package eagleseye.elden_flasks.internals.item.custom;

import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.util.ComponentUtils;
import eagleseye.elden_flasks.internals.util.VisualsUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class EnhancerItem extends Item {
    private final EnhancerType enhancerType;

    public EnhancerItem(EnhancerType enhancerType) {
        super(new Item.Settings()
                .maxCount(16)
                .rarity(Rarity.RARE)
        );
        this.enhancerType = enhancerType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack offHandItem = user.getInventory().offHand.get(0);

        if (offHandItem.getItem() instanceof HealingFlaskItem) {
            if (enhancerType == EnhancerType.USES) {
                if (ComponentUtils.getMaxUses(offHandItem) >= EldenFlasks.flasksConfig.healing_flask.max_uses_amount) return flaskMaxed(user, hand);
                ComponentUtils.setMaxUses(offHandItem, ComponentUtils.getMaxUses(offHandItem) +
                        EldenFlasks.enhancersConfig.uses_enhancer_amount);
            }

            else if (enhancerType == EnhancerType.HEALING) {
                if (ComponentUtils.getHealAmount(offHandItem) >= EldenFlasks.flasksConfig.healing_flask.max_healing_amount) return flaskMaxed(user, hand);
                ComponentUtils.setHealAmount(offHandItem, ComponentUtils.getHealAmount(offHandItem) +
                        EldenFlasks.enhancersConfig.healing_enhancer_amount);
            }
        } else {
            return cancelUsage(user, hand);
        }

        user.getStackInHand(hand).decrement(1);
        VisualsUtils.sendEnhanceVisuals(user);

        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (enhancerType == EnhancerType.USES) {
            tooltip.add(Text.translatable("tooltip.elden_flasks.enhancer_item.uses",
                   EldenFlasks.enhancersConfig.uses_enhancer_amount, EldenFlasks.flasksConfig.healing_flask.max_uses_amount).formatted(Formatting.GRAY));
        } else if (enhancerType == EnhancerType.HEALING) {
            tooltip.add(Text.translatable("tooltip.elden_flasks.enhancer_item.healing",
                    (int)EldenFlasks.enhancersConfig.healing_enhancer_amount, (int)EldenFlasks.flasksConfig.healing_flask.max_healing_amount).formatted(Formatting.GRAY));
        }

        tooltip.add(Text.translatable("tooltip.elden_flasks.enhancer_item.usage_info").formatted(Formatting.DARK_GRAY));
    }

    private TypedActionResult<ItemStack> cancelUsage(PlayerEntity user, Hand hand){
        user.sendMessage(Text.translatable("message.elden_flasks.cancel_enhancer_usage").formatted(Formatting.RED), true);
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    private TypedActionResult<ItemStack> flaskMaxed(PlayerEntity user, Hand hand) {
        user.sendMessage(Text.translatable("message.elden_flasks.enhancer_flask_maxed", enhancerType.getDisplayName()).formatted(Formatting.RED), true);
        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
