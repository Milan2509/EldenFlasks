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
}
