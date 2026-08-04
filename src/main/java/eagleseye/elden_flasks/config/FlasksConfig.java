package eagleseye.elden_flasks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "flasks")
public class FlasksConfig implements ConfigData {
    @Comment("Config options for the Healing Flask")
    public HealingFlaskConfig healing_flask = new HealingFlaskConfig();

    public static class HealingFlaskConfig {
        @Comment("Base amount of half hearths healed by the healing flask")
        public int base_healing_amount = 4;
        @Comment("Base amount of charges the flask has before needing to recharge")
        public int base_uses_count = 3;
        @Comment("Amount of kills needed to recharge 1 charge of the flask")
        public int recharge_kill_amount = 5;
    }
}
