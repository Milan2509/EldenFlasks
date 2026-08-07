package eagleseye.elden_flasks.internals.component;

import net.minecraft.item.ItemStack;

public class ComponentUtil {
    public static float getHealAmount(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.HEAL_AMOUNT) != null){
            return stack.get(EldenFlaskComponents.HEAL_AMOUNT);
        } else {
            return 0;
        }
    }

    public static void setHealAmount(ItemStack stack, float amount) {
        if (stack.get(EldenFlaskComponents.HEAL_AMOUNT) != null){
            stack.set(EldenFlaskComponents.HEAL_AMOUNT, amount);
        }
    }

    public static int getMaxUses(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.FLASK_MAX_USES) != null){
            return stack.get(EldenFlaskComponents.FLASK_MAX_USES);
        } else {
            return 0;
        }
    }

    public static void setMaxUses(ItemStack stack, int amount) {
        if (stack.get(EldenFlaskComponents.FLASK_MAX_USES) != null){
            stack.set(EldenFlaskComponents.FLASK_MAX_USES, amount);
        }
    }

    public static int getCurrentUses(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.FLASK_CURRENT_USES) != null){
            return stack.get(EldenFlaskComponents.FLASK_CURRENT_USES);
        } else {
            return 0;
        }
    }

    public static void setCurrentUses(ItemStack stack, int amount) {
        if (stack.get(EldenFlaskComponents.FLASK_CURRENT_USES) != null){
            stack.set(EldenFlaskComponents.FLASK_CURRENT_USES, amount);
        }
    }

    public static int getRequiredKillCount(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.RECHARGE_KILL_REQUIREMENT) != null){
            return stack.get(EldenFlaskComponents.RECHARGE_KILL_REQUIREMENT);
        } else {
            return 0;
        }
    }

    public static void setRequiredKillCount(ItemStack stack, int amount) {
        if (stack.get(EldenFlaskComponents.RECHARGE_KILL_REQUIREMENT) != null){
            stack.set(EldenFlaskComponents.RECHARGE_KILL_REQUIREMENT, amount);
        }
    }

    public static int getCurrentKillCount(ItemStack stack) {
        if (stack.get(EldenFlaskComponents.RECHARGE_KILL_CURRENT) != null){
            return stack.get(EldenFlaskComponents.RECHARGE_KILL_CURRENT);
        } else {
            return 0;
        }
    }

    public static void setCurrentKillCount(ItemStack stack, int amount) {
        if (stack.get(EldenFlaskComponents.RECHARGE_KILL_CURRENT) != null){
            stack.set(EldenFlaskComponents.RECHARGE_KILL_CURRENT, amount);
        }
    }

    public static void incrementFlaskKillCount(ItemStack stack) {
        setCurrentKillCount(stack, getCurrentKillCount(stack) + 1);
    }
}
