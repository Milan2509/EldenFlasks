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
    public double movementSpeedValue = 0.05;
    public String movementSpeedOperation = "MULTIPLY_BASE";

    public double attackDamageValue = 0.05;
    public String attackDamageOperation = "MULTIPLY_BASE";

    public double armorValue = 2;
    public String armorOperation = "ADDITION";

    public double toughnessValue = 1;
    public String toughnessOperation = "ADDITION";

    public double maxHealthValue = 4;
    public String maxHealthOperation = "ADDITION";

    public double attackSpeedValue = 0.02;
    public String attackSpeedOperation = "MULTIPLY_BASE";

    public double luckValue = 2;
    public String luckOperation = "ADDITION";

    public double knockbackResistanceValue = 1;
    public String knockbackResistanceOperation = "ADDITION";
    @Comment("""
            Modded Buffs
            The following buffs require a mod to be loaded for them to register.
            
            Spell Power Attributes
            """)
    public double firePowerValue = 0.05;
    public String firePowerOperation = "MULTIPLY_BASE";

    public double frostPowerValue = 0.05;
    public String frostPowerOperation = "MULTIPLY_BASE";

    public double arcanePowerValue = 0.05;
    public String arcanePowerOperation = "MULTIPLY_BASE";

    public double healingPowerValue = 0.05;
    public String healingPowerOperation = "MULTIPLY_BASE";

    @Comment("Eternal Attributes (Death Knights)")
    public double bloodPowerValue = 0.05;
    public String bloodPowerOperation = "MULTIPLY_BASE";

    public double unholyPowerValue = 0.05;
    public String unholyPowerOperation = "MULTIPLY_BASE";

    @Comment("Ranged Weapon API")
    public double rangedDamageValue = 0.05;
    public String rangedDamageOperation = "MULTIPLY_BASE";

    public double drawSpeedValue = 0.02;
    public String drawSpeedOperation = "MULTIPLY_BASE";

    @Comment("Combat Roll")
    public double rollRechargeValue = 1;
    public String rollRechargeOperation = "MULTIPLY_BASE";

    public double rollCountValue = 1;
    public String rollCountOperation = "ADDITION";
}
