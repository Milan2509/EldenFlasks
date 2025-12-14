package eagleseye.eldenflasks.config;

import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static eagleseye.eldenflasks.EldenFlasks.LOOT_CONFIG;

@Config(name = "eldenflasks/loot_v2", wrapperName = "EldenFlasksLootConfig")
public class LootConfigModel {
    @Comment("""
            The loot tables to inject the enhancer items
            
            Format: <IDENTIFIER>|<DROP_RATE>
            
            <IDENTIFIER>: unique loot table identifier (modid:loot_table), can be found using the /loot command
            <DROP_RATE>: drop chance 0-1 where 1 = 100%
            """)
    public List<String> tearsLootTable = List.of(
            "minecraft:chests/ancient_city|0.1",
            "minecraft:chests/end_city_treasure|0.3",

            "minecraft:entities/wither|1.0",
            "minecraft:entities/ender_dragon|1.0",
            "minecraft:entities/warden|1.0",

            "minecells:concierge|1.0",
            "minecraft:conjunctivius|1.0",

            "bosses_of_mass_destruction:lich|1.0",
            "bosses_of_mass_destruction:gauntlet|1.0",
            "bosses_of_mass_destruction:void_blossom|1.0",

            "cataclysm:ignis|1.0",
            "cataclysm:ender_golem|1.0",
            "cataclysm:netherite_monstrosity|1.0",
            "cataclysm:leviathan|1.0",
            "cataclysm:ancient_remnant|1.0",
            "cataclysm:harbinger|1.0",
            "cataclysm:scylla|1.0",

            "mowziesmobs:frostmaw|1.0",
            "mowziesmobs:ferrous_wraughtnaut|1.0"
    );

    public List<String> runeLootTable = List.of(
            "minecraft:chests/bastion_treasure|0.5",
            "minecraft:chests/abandoned_mineshaft|0.1",
            "minecraft:chests/desert_pyramid|0.2",
            "minecraft:chests/bastion_treasure|0.5",
            "minecraft:chests/jungle_temple|0.1",
            "minecraft:chests/bastion_treasure|0.5",
            "minecraft:chests/stronghold_library|0.7"
    );


}
