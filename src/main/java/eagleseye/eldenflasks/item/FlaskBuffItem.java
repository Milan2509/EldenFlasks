package eagleseye.eldenflasks.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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
        tooltip.add(Text.literal(buffId));
    }
}
