package eagleseye.eldenflasks.config;
import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.Config;

import java.util.List;

@Config(name = "eldenflasks/loot_v1", wrapperName = "EldenFlasksLootConfig")
public class LootConfigModel {
    @Comment("""
            The loot tables to inject the enhancer items
            
            Format: <IDENTIFIER>|<DROP_RATE>
            
            <IDENTIFIER>: unique loot table identifier, can be found using the /loot command
            <DROP_RATE>: float of drop chance 0-1 where 1 = 100%
            """)
    public String[] pearlLootTables = {
            "minecraft:chests/buried_treasure|0.8",
            "minecraft:entities/elder_guardian|0.5",
            "minecraft:chests/shipwreck_treasure|0.3",
            "minecraft:chests/igloo_chest|0.8",
            "minecraft:chests/simple_dungeon|0.5",
            "minecraft:chests/woodland_mansion|0.8"
    };
    public String[] tearsLootTable = {
            "minecraft:entities/ender_dragon|1.0",
            "minecraft:chests/ancient_city|0.5",
            "minecraft:chests/end_city_treasure|0.3",
            "minecraft:entities/wither|1.0"
    };
    public String[] runeLootTable = {
            "minecraft:chests/bastion_treasure|0.5",
            "minecraft:chests/abandoned_mineshaft|0.3",
            "minecraft:chests/desert_pyramid|0.2",
            "minecraft:entities/warden|1.0",
            "minecraft:chests/jungle_temple|0.1",
            "minecraft:chests/stronghold_library|0.7"
    };
}
