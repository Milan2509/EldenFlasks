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
@Config(name = "eldenflasks/flasks_v2", wrapperName = "EldenFlasksFlaskConfig")
public class FlasksConfigModel {
    @SectionHeader("general")
    @Comment("When more flasks are held then the flasks aren't usable.")
    public int maxHeldHealingFlasks = 1;
    @Comment("How many kills are required for the healing flask to get 1 additional charge")
    public int rechargeKillRequirement = 8;
    @Comment("Keep the flask the player's inventory on death")
    public boolean keepHealingFlaskOnDeath = true;
    @Comment("Which entities won't count towards the kill flask recharging")
    public List<String> entityKillBlacklist = new ArrayList<>();
    @Comment("Which entities should fully recharge the healing flasks charges on death")
    public List<String> fullyRechargeEntities = new ArrayList<>();

    @Comment("enable/disable the how to recharge tooltip")
    public boolean rechargeTooltip = true;

    @SectionHeader("baseStats")
    public int maxCharges = 3;
    public float healing = 8;
    public int drinkTime = 25;

    @SectionHeader("statLimits")
    public int maxChargeLimit = 12;
    public float healingLimit = 20;

    @SectionHeader("enhancerModifiers")
    public int maxChargeModifier = 1;
    public float healingModifier = 2;

    @SectionHeader("modIntegrations")
    public boolean inCombatIntegration = true;

    @ExcludeFromScreen
    @Comment("DO NOT TOUCH PLS")
    public boolean firstLoad = true;
}
