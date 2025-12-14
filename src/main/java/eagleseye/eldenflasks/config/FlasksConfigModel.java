package eagleseye.eldenflasks.config;

import blue.endless.jankson.Comment;
import eagleseye.eldenflasks.EldenFlasks;
import io.wispforest.owo.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Modmenu(modId = EldenFlasks.MOD_ID)
@Config(name = "eldenflasks/flasks_v2", wrapperName = "EldenFlasksFlaskConfig")
public class FlasksConfigModel {
    @SectionHeader("healingFlask")
    @Comment("When more healing flasks are held then the flasks aren't usable.")
    public int maxHeldHealingFlasks = 1;

    @Comment("The stats of the healing flask and enhancer modifiers")
    @Nest
    public HealingFlaskStats healingFlaskStats = new HealingFlaskStats();

    public static class HealingFlaskStats{
        @Comment("The stats of the healing flask")
        public int maxCharges = 3;
        public float healing = 8;
        public int drinkTime = 25;

        @Comment("the maximum healing and charges the healing flask can have")
        public int maxChargeLimit = 12;
        public float healingLimit = 20;

        @Comment("The amount of which the enhancer modifier modifies the flask")
        public int maxChargeModifier = 1;
        public float healingModifier = 2;
    }

    @Comment("How many kills are required for the healing flask to get 1 recharge")
    public int rechargeKillRequirement = 8;

    @Comment("Which entities won't count towards the kill flask recharging")
    public List<String> entityKillBlacklist = new ArrayList<>();
    @Comment("Which entities should fully recharge the healing flasks charges on death")
    public List<String> fullyRechargeEntities = List.of(
            "minecraft:wither",
            "minecraft:ender_dragon",

            "minecells:concierge",
            "minecraft:conjunctivius",

            "bosses_of_mass_destruction:lich",
            "bosses_of_mass_destruction:gauntlet",
            "bosses_of_mass_destruction:void_blossom",

            "cataclysm:ignis",
            "cataclysm:ender_golem",
            "cataclysm:netherite_monstrosity",
            "cataclysm:leviathan",
            "cataclysm:ancient_remnant",
            "cataclysm:harbinger",
            "cataclysm:scylla",

            "mowziesmobs:frostmaw",
            "mowziesmobs:ferrous_wraughtnaut"
            
    );

    //TODO: remove this and replace it with a tooltip only showing when shift is held.
    @Comment("enable/disable the how to recharge tooltip")
    public boolean rechargeTooltip = true;

    @SectionHeader("mixedFlask")
    @Comment("When more mixed flasks are held then the flasks aren't usable.")
    public int maxHeldMixedFlasks = 1;
    @Comment("Duration of the buffed effect, in ticks (20 = 1 sec)")
    public int buffDuration = 1200;

    @SectionHeader("modIntegrations")
    @Comment("Integration with the In Combat mod, stops the player from recharging the flask at a campfire while in combat")
    public boolean inCombatIntegration = true;

//    @ExcludeFromScreen
//    @Comment("DO NOT TOUCH PLS")
//    public boolean firstLoad = true;
}
