package eagleseye.elden_flasks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = "flasks")
public class FlasksConfig implements ConfigData {
    @Comment("Config options for the Healing Flask")
    public HealingFlaskConfig healing_flask = new HealingFlaskConfig();

    public static class HealingFlaskConfig {
        @Comment("Base amount of charges the flask has before needing to recharge (requires restart to take effect)")
        public int base_uses_amount = 3;
        @Comment("Base amount of half hearths healed by the healing flask (requires restart to take effect)")
        public float base_healing_amount = 4;
        @Comment("Amount of kills needed to recharge 1 charge of the flask")
        public int recharge_kill_amount = 5;
        @Comment("Blocks that will recharge the healing flask when right clicked")
        public List<String> recharge_blocks = new ArrayList<>(){
            {
                add("minecraft:campfire");
                add("minecraft:soul_campfire");
            }
        };
    }
}
