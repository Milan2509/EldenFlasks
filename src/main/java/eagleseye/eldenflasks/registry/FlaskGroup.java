package eagleseye.eldenflasks.registry;

import eagleseye.eldenflasks.EldenFlasks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FlaskGroup {
    private static List<Item> buffItems = new ArrayList<>();

    public static final ItemGroup FLASKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EldenFlasks.MOD_ID, "generic"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.eldenflasks.generic"))
                    .icon(() -> new ItemStack(ItemRegistry.HEALTH_FLASK)).entries((displayContext, entries) -> {
                        entries.add(BlockRegistry.MIXER);
                        entries.add(ItemRegistry.HEALTH_FLASK);
                        entries.add(ItemRegistry.MIXING_FLASK);
//                        entries.add(ItemRegistry.PEARL);
                        entries.add(ItemRegistry.SACRED_TEARS);
                        entries.add(ItemRegistry.RUNE);
                        // Add buff items to group
                        for (Item item : buffItems){
                            entries.add(item);
                        }
                    }).build());

    public static void init(){}

    public static List<Item> getBuffItems() {
        return buffItems;
    }

    public static void addBuffItem(Item buffItem) {
        buffItems.add(buffItem);
    }
}
