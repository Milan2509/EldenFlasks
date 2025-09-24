package eagleseye.eldenflasks.item;

import eagleseye.eldenflasks.buff.BuffManager;
import net.minecraft.client.item.TooltipContext;
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
        //TODO: Fix BuffItem tooltips
//        if (BuffManager.getBuff(buffId) == null) {
//            tooltip.add(Text.translatable("buff.invalid.desc", BuffManager.getBuff(buffId)));
//        }
//        tooltip.add(Text.translatable(BuffManager.getBuff(buffId).getName()).formatted(Formatting.GOLD));
//        tooltip.add(Text.translatable(BuffManager.getBuff(buffId).getDesc()).formatted(Formatting.DARK_GRAY));
    }
}
