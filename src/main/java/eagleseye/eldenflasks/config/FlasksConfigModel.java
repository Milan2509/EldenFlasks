package eagleseye.eldenflasks.config;

import blue.endless.jankson.Comment;
import eagleseye.eldenflasks.EldenFlasks;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = EldenFlasks.MOD_ID)
@Config(name = "eldenflasks/flasks_v1", wrapperName = "EldenFlasksFlaskConfig")
public class FlasksConfigModel {
    @SectionHeader("baseStats")
    public int maxCharges = 3;
    public float healing = 8;
    public int drinkTime = 40;

    @SectionHeader("statLimits")
    public int maxChargeLimit = 6;
    public float healingLimit = 20;
    public int drinkTimeLimit = 20;

    @SectionHeader("enhancerModifiers")
    public int maxChargeModifier = 1;
    public float healingModifier = 2;
    public int drinkTimeModifier = 5;
}
