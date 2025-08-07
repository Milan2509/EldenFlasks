package eagleseye.eldenflasks.item.custom;

import eagleseye.eldenflasks.EldenFlasks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealingEnhancerItem extends Item {
    public Text lore;
    public Text desc;

    private static String loreString;

    public HealingEnhancerItem(Settings settings, float modifier, float maxModifier, String str) {
        super(settings.rarity(Rarity.RARE).maxCount(8));
        loreString = str;

        lore = Text.literal(loreString)
                .formatted(Formatting.GOLD, Formatting.ITALIC);
        desc = Text.literal("+" + (int) modifier + " Healing to Flask of Healing"
                + " (Max: " + (int) maxModifier + ")").formatted(Formatting.GRAY);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (loreString != null) tooltip.add(lore);
        tooltip.add(desc);
        tooltip.add(Text.literal("Applied in the Flask Mixer").formatted(Formatting.DARK_GRAY));
    }
}
