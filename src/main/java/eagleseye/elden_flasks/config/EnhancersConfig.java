package eagleseye.elden_flasks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.LinkedHashSet;
import java.util.Set;

@Config(name = "enhancers")
public class EnhancersConfig implements ConfigData {
    @Comment("How much the uses enhancer should increase the healing flask's uses")
    public int uses_enhancer_amount = 1;
    @Comment("How much the healing enhancer should increase the healing flask's healing")
    public float healing_enhancer_amount = 2;

    @Comment("The loot tables in which the uses enhancer can be found. Format: loot_table_id|chance")
    public Set<String> uses_enhancer_loot_tables = new LinkedHashSet<>() {
        {
            add("minecraft:chests/bastion_treasure|0.5");
            add("minecraft:chests/abandoned_mineshaft|0.1");
            add("minecraft:chests/desert_pyramid|0.2");
            add("minecraft:chests/jungle_temple|0.1");
            add("minecraft:chests/stronghold_library|0.7");
        }
    };

    @Comment("The loot tables in which the healing enhancer can be found. Format: loot_table_id|chance")
    public Set<String> healing_enhancer_loot_tables = new LinkedHashSet<>() {
        {

            add("minecraft:chests/ancient_city|0.1");
            add("minecraft:chests/end_city_treasure|0.3");

            add("minecraft:entities/wither|1.0");
            add("minecraft:entities/ender_dragon|1.0");
            add("minecraft:entities/warden|1.0");

            add("minecells:concierge|1.0");
            add("minecraft:conjunctivius|1.0");

            add("bosses_of_mass_destruction:lich|1.0");
            add("bosses_of_mass_destruction:gauntlet|1.0");
            add("bosses_of_mass_destruction:void_blossom|1.0");

            add("cataclysm:ignis|1.0");
            add("cataclysm:ender_golem|1.0");
            add("cataclysm:netherite_monstrosity|1.0");
            add("cataclysm:leviathan|1.0");
            add("cataclysm:ancient_remnant|1.0");
            add("cataclysm:harbinger|1.0");
            add("cataclysm:scylla|1.0");

            add("mowziesmobs:frostmaw|1.0");
            add("mowziesmobs:ferrous_wraughtnaut|1.0");

        }
    };
}
