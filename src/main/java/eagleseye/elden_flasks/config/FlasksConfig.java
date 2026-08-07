package eagleseye.elden_flasks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Config(name = "flasks")
public class FlasksConfig implements ConfigData {
    @Comment("Config options for the Healing Flask")
    public HealingFlaskConfig healing_flask = new HealingFlaskConfig();

    public static class HealingFlaskConfig {
        @Comment("Base amount of charges the flask has before needing to recharge (requires restart to take effect)")
        public int base_uses_amount = 3;
        @Comment("Maximum amount of uses the flask can reach when fully enhanced")
        public int max_uses_amount = 8;
        @Comment("Base amount of half hearths healed by the healing flask (requires restart to take effect)")
        public float base_healing_amount = 4;
        @Comment("Maximum amount of healing the flask can reach when fully enhanced")
        public float max_healing_amount = 12;
        @Comment("Amount of kills needed to recharge 1 charge of the flask")
        public int recharge_kill_requirement = 5;
        @Comment("Amount of uses recharged from reaching the recharge_kill_requirement")
        public int recharge_from_kill_amount = 1;
        @Comment("Blocks that will recharge the healing flask when right-clicked")
        public Set<String> recharge_blocks = new LinkedHashSet<>(){
            {
                add("minecraft:campfire");
                add("minecraft:soul_campfire");
            }
        };
    }
}
