package eagleseye.eldenflasks.loot;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.item.ItemRegistry;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

public class EnhancerLootInjections {
    private static final String[] pearlLootTables = EldenFlasks.LOOT_CONFIG.pearlLootTables();
    private static final String[] tearsLootTables = EldenFlasks.LOOT_CONFIG.tearsLootTable();
    private static final String[] runeLootTables = EldenFlasks.LOOT_CONFIG.runeLootTable();

    public static void parseLootTableConfig(){
        for (String table : pearlLootTables) {
            if(table.contains(":") && table.contains("|")) {
                float dropRate = Float.parseFloat(StringUtils.substringAfter(table, "|"));
                Identifier id = createLootTableId(table);

                modifyLootTables(id, ItemRegistry.PEARL, dropRate);
            }
        }
        for (String table : tearsLootTables) {
            if(table.contains(":") && table.contains("|")) {
                float dropRate = Float.parseFloat(StringUtils.substringAfter(table, "|"));
                Identifier id = createLootTableId(table);

                modifyLootTables(id, ItemRegistry.SACRED_TEARS, dropRate);
            }
        }
        for (String table : runeLootTables) {
            if(table.contains(":") && table.contains("|")) {
                float dropRate = Float.parseFloat(StringUtils.substringAfter(table, "|"));
                Identifier id = createLootTableId(table);

                modifyLootTables(id, ItemRegistry.RUNE, dropRate);
            }
        }
    }

    private static void modifyLootTables(Identifier tableToModify, Item item, float dropRate){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, builder, lootTableSource) -> {
                    if (tableToModify.equals(id)){
                        LootPool.Builder poolBuilder = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(dropRate))
                                .with(ItemEntry.builder(item))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                        builder.pool(poolBuilder.build());
                    }
                });
    }

    private static Identifier createLootTableId(String table){
        String modId = StringUtils.substringBefore(table, ":");
        String tableIdWithDropRate = StringUtils.substringAfter(table, ":");
        String tableId = StringUtils.substringBefore(tableIdWithDropRate, "|");

        return new Identifier(modId, tableId);
    }
}
