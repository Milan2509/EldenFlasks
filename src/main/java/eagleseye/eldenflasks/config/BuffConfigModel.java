package eagleseye.eldenflasks.config;

import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.Config;

@Config(name = "eldenflasks/buffs_v1", wrapperName = "EldenFlasksBuffConfig")
public class BuffConfigModel {
    @Comment("""
            The config for all buffs available.
            The following can be configured
            
            - Modifier amount
                - Double
            - Operation
                - ADDITION
                - MULTIPLY_BASE
                - MULTIPLY_TOTAL
            """)
    public double attackDamageValue = 0.06;
    public String attackDamageOperation = "MULTIPLY_BASE";

    public double attackSpeedValue = 0.04;
    public String attackSpeedOperation = "MULTIPLY_BASE";

    public double maxHealthValue = 4;
    public String maxHealthOperation = "ADDITION";

    public double movementSpeedValue = 0.05;
    public String movementSpeedOperation = "MULTIPLY_BASE";

    public double armorValue = 2;
    public String armorOperation = "ADDITION";

    public double toughnessValue = 1;
    public String toughnessOperation = "ADDITION";

    public double luckValue = 2;
    public String luckOperation = "ADDITION";

    @Comment("This value makes the player completely immune to knockback, the translation tooltip is changed to reflect this!")
    public double knockbackResistanceValue = 1;
    public String knockbackResistanceOperation = "ADDITION";
    @Comment("""
            Modded Buffs
            The following buffs require a mod to be loaded for them to register.
            
            If the modded buffs should be disabled. NOTE: the required mod still needs to be loaded! 
            """)
    public boolean disableModdedBuffs = false;

    @Comment("Ranged Weapon API")
    public double rangedDamageValue = 0.06;
    public String rangedDamageOperation = "MULTIPLY_BASE";

    public double drawSpeedValue = 0.02;
    public String drawSpeedOperation = "MULTIPLY_BASE";

    @Comment("Combat Roll")
    public double rollRechargeValue = 0.1;
    public String rollRechargeOperation = "MULTIPLY_BASE";

    public double rollCountValue = 1;
    public String rollCountOperation = "ADDITION";

    @Comment("Spell Power")
    public double firePowerValue = 0.06;
    public String firePowerOperation = "MULTIPLY_BASE";

    public double frostPowerValue = 0.06;
    public String frostPowerOperation = "MULTIPLY_BASE";

    public double arcanePowerValue = 0.06;
    public String arcanePowerOperation = "MULTIPLY_BASE";

    public double healingPowerValue = 0.06;
    public String healingPowerOperation = "MULTIPLY_BASE";

    public double spellHasteValue = 0.04;
    public String spellHasteOperation = "MULTIPLY_BASE";

    public double spellCritChanceValue = 0.06;
    public String spellCritChanceOperation = "MULTIPLY_BASE";

    public double spellCritDamageValue = 0.06;
    public String spellCritDamageOperation = "MULTIPLY_BASE";

    @Comment("Eternal Attributes (Death Knights)")
    public double bloodPowerValue = 0.06;
    public String bloodPowerOperation = "MULTIPLY_BASE";

    public double unholyPowerValue = 0.06;
    public String unholyPowerOperation = "MULTIPLY_BASE";

    @Comment("Eldritch End")
    public double corruptionValue = 15;
    public String corruptionOperation = "ADDITION";

    public double corruptionResistanceValue = 15;
    public String corruptionResistanceOperation = "ADDITION";
}
