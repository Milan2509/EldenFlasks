package eagleseye.eldenflasks.item;

import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.buff.FlaskBuff;
import eagleseye.eldenflasks.util.FlaskBuffUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlaskBuffItem extends Item {
    private final String buffId;
    public FlaskBuffItem(Settings settings, String buffId) {
        super(settings.rarity(Rarity.RARE).maxCount(1));
        this.buffId = buffId;
    }

    public String getBuffId(){
        return this.buffId;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        FlaskBuff buff = BuffManager.getBuff(buffId);
        // Checks if the buff exists, prevents crashes
        if (buff == null) {
            tooltip.add(Text.translatable("buff.invalid.desc", BuffManager.getBuff(buffId)));
            return;
        }

        tooltip.add(Text.translatable(buff.getName()).formatted(Formatting.GOLD));

        // Description changes based on operation
        tooltip.add(FlaskBuffUtils.createBuffDescription(buff));
    }


}
