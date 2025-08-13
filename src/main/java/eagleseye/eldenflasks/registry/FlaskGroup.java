package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.registry.ItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FlaskGroup {
    public static final ItemGroup FLASKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EldenFlasks.MOD_ID, "generic"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.eldenflasks.generic"))
                    .icon(() -> new ItemStack(ItemRegistry.HEALTH_FLASK)).entries((displayContext, entries) -> {
                        entries.add(BlockRegistry.MIXER);
                        entries.add(ItemRegistry.HEALTH_FLASK);
                        entries.add(ItemRegistry.PEARL);
                        entries.add(ItemRegistry.SACRED_TEARS);
                        entries.add(ItemRegistry.RUNE);
                    }).build());

    public static void init(){}
}
