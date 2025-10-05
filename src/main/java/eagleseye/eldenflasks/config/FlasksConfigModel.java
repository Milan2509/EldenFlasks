package eagleseye.eldenflasks.config;

import blue.endless.jankson.Comment;
import eagleseye.eldenflasks.EldenFlasks;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.ExcludeFromScreen;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

import java.util.ArrayList;
import java.util.List;

@Modmenu(modId = EldenFlasks.MOD_ID)
@Config(name = "eldenflasks/flasks_v1", wrapperName = "EldenFlasksFlaskConfig")
public class FlasksConfigModel {
    @SectionHeader("general")
    @Comment("When more flasks are held then the flasks aren't usable.")
    public int maxHeldHealingFlasks = 1;
    @Comment("How many kills are required for the healing flask to get 1 additional charge")
    public int rechargeKillRequirement = 8;
    @Comment("Which entities won't count towards the kill flask recharging")
    public List<String> entityKillBlacklist = new ArrayList<>();
    @Comment("Which entities should fully recharge the healing flasks charges on death")
    public List<String> fullyRechargeEntities = new ArrayList<>();

    @SectionHeader("baseStats")
    public int maxCharges = 3;
    public float healing = 8;
    public int drinkTime = 40;

    @SectionHeader("statLimits")
    public int maxChargeLimit = 12;
    public float healingLimit = 20;
    public int drinkTimeLimit = 20;

    @SectionHeader("enhancerModifiers")
    public int maxChargeModifier = 1;
    public float healingModifier = 2;
    public int drinkTimeModifier = 5;

    @SectionHeader("modIntegrations")
    public boolean inCombatIntegration = true;

    @ExcludeFromScreen
    @Comment("DO NOT TOUCH PLS")
    public boolean firstLoad = true;
}
