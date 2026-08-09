package eagleseye.elden_flasks.internals.util;

import com.llamalad7.mixinextras.lib.apache.commons.StringUtils;
import eagleseye.elden_flasks.EldenFlasks;
import eagleseye.elden_flasks.internals.item.EldenFlasksItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Set;

public class LootInjections {
    public static void register() {
        Set<String> usesLootTables = EldenFlasks.enhancersConfig.uses_enhancer_loot_tables;
        Set<String> healingLootTables = EldenFlasks.enhancersConfig.healing_enhancer_loot_tables;

        for (String table : usesLootTables) {
            float dropRate = Float.parseFloat(StringUtils.substringAfter(table, "|"));
            Identifier id = createLootTableId(table);

            modifyLootTables(id, dropRate, EldenFlasksItems.ENHANCER_USES);
        }

        for (String table : healingLootTables) {
            float dropRate = Float.parseFloat(StringUtils.substringAfter(table, "|"));
            Identifier id = createLootTableId(table);

            modifyLootTables(id, dropRate, EldenFlasksItems.ENHANCER_HEALING);
        }
    }

    private static void modifyLootTables(Identifier table, float dropRate, Item item) {
        LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
            if (table.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(dropRate))
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                builder.pool(poolBuilder.build());
            }
        });
    }

    private static Identifier createLootTableId(String table) {
        String namespace = StringUtils.substringBefore(table, ":");
        String tableIdWithDropRate = StringUtils.substringAfter(table, ":");
        String tableId = StringUtils.substringBefore(tableIdWithDropRate, "|");

        return Identifier.of(namespace, tableId);
    }
}