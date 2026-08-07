package eagleseye.elden_flasks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "enhancers")
public class EnhancersConfig implements ConfigData {
    @Comment("How much the uses enhancer should increase the healing flask's uses")
    public int uses_enhancer_amount = 1;
    @Comment("How much the healing enhancer should increase the healing flask's healing")
    public float healing_enhancer_amount = 2;
}
