package eagleseye.elden_flasks.config;

import eagleseye.elden_flasks.EldenFlasks;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = EldenFlasks.MOD_ID)
public class EldenFlasksConfigWrapper extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Gui.Excluded
    public FlasksConfig flasks = new FlasksConfig();
    public EnhancersConfig enhancers = new EnhancersConfig();
}
