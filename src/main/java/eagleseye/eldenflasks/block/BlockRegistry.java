package eagleseye.eldenflasks.block;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.block.custom.FlaskMixerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockRegistry {
    public static final Block MIXER = register("mixer",
            new FlaskMixerBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD).nonOpaque()));

    private static Block register(String id, Block block){
        registerItem(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(EldenFlasks.MOD_ID, id), block);
    }

    private static BlockItem registerItem(String id, Block block){
        return Registry.register(Registries.ITEM, new Identifier(EldenFlasks.MOD_ID, id),
                new BlockItem(block, new FabricItemSettings()) {
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(Text.literal("Used to enhance the Flask of Healing").formatted(Formatting.GRAY));
                    }
                });
    }

    public static void init(){}
}
