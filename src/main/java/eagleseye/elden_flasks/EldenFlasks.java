package eagleseye.elden_flasks;

import eagleseye.elden_flasks.config.EnhancersConfig;
import eagleseye.elden_flasks.config.FlasksConfig;
import eagleseye.elden_flasks.config.EldenFlasksConfigWrapper;
import eagleseye.elden_flasks.internals.EldenFlasksEvents;
import eagleseye.elden_flasks.internals.client.ModelPredicates;
import eagleseye.elden_flasks.internals.item.EldenFlasksGroup;
import eagleseye.elden_flasks.internals.item.EldenFlasksItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class EldenFlasks implements ModInitializer {
    public static final String MOD_ID = "elden_flasks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static FlasksConfig flasksConfig;
    public static EnhancersConfig enhancersConfig;

    @Override
    public void onInitialize() {
        // Config
        AutoConfig.register(EldenFlasksConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        flasksConfig = AutoConfig.getConfigHolder(EldenFlasksConfigWrapper.class).getConfig().flasks;
        enhancersConfig = AutoConfig.getConfigHolder(EldenFlasksConfigWrapper.class).getConfig().enhancers;

        // Register
        EldenFlasksItems.register();
        EldenFlasksGroup.register();
        EldenFlasksEvents.register();
    }
}
