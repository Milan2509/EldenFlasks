package eagleseye.eldenflasks.util;

import eagleseye.eldenflasks.buff.FlaskBuff;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FlaskBuffUtils {
    public static Text createBuffDescription(FlaskBuff buff) {
        if (buff == null) return Text.literal("BUFF NOT FOUND");

        if (buff.getOperation() == EntityAttributeModifier.Operation.ADDITION) {
            return Text.translatable(buff.getDesc(), buff.getValue()).formatted(Formatting.DARK_GRAY);
        }
        if (buff.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_BASE || buff.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_TOTAL) {
            return Text.translatable(buff.getDesc(), round(buff.getValue() * 100, 2) + "%").formatted(Formatting.DARK_GRAY);
        }

        return Text.translatable(buff.getDesc());
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * operationFromString
     *
     * @param opString string of the operation; defaults to ADDITION
     * @return Entity Attribute Modifier Operation
     */
    public static EntityAttributeModifier.Operation operationFromString(String opString) {
        if (opString == null) return EntityAttributeModifier.Operation.ADDITION;

        return switch (opString) {
            case "MULTIPLY_BASE" -> EntityAttributeModifier.Operation.MULTIPLY_BASE;
            case "MULTIPLY_TOTAL" -> EntityAttributeModifier.Operation.MULTIPLY_TOTAL;
            default -> EntityAttributeModifier.Operation.ADDITION;
        };
    }
}
