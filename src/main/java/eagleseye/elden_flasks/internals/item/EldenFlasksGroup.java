package eagleseye.elden_flasks.internals.item;

import eagleseye.elden_flasks.EldenFlasks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EldenFlasksGroup {
    public static final ItemGroup GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EldenFlasks.MOD_ID, "generic"),
            FabricItemGroup.builder().icon(() -> new ItemStack(EldenFlasksItems.HEALING_FLASK))
                    .displayName(Text.translatable("itemgroup.elden_flasks.generic"))
                    .entries((displayContext, entries) -> {
                        entries.add(EldenFlasksItems.HEALING_FLASK);
                        entries.add(EldenFlasksItems.ENHANCER_USES);
                        entries.add(EldenFlasksItems.ENHANCER_HEALING);
                    }).build());

    public static void register() {
        EldenFlasks.LOGGER.info("Registering Item Group for: " + EldenFlasks.MOD_ID);
    }
}
