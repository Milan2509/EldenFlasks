package eagleseye.eldenflasks;

import eagleseye.eldenflasks.registry.BlockRegistry;
import eagleseye.eldenflasks.registry.BlockEntityRegistry;
import eagleseye.eldenflasks.config.EldenFlasksFlaskConfig;
import eagleseye.eldenflasks.config.EldenFlasksLootConfig;
import eagleseye.eldenflasks.registry.FlaskGroup;
import eagleseye.eldenflasks.registry.ItemRegistry;
import eagleseye.eldenflasks.util.EnhancerLootInjections;
import eagleseye.eldenflasks.screen.ScreenHandlerRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EldenFlasks implements ModInitializer {
	public static final String MOD_ID = "eldenflasks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EldenFlasksFlaskConfig FLASKS_CONFIG = EldenFlasksFlaskConfig.createAndLoad();
	public static final EldenFlasksLootConfig LOOT_CONFIG = EldenFlasksLootConfig.createAndLoad();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing: Elden Flasks");
		ItemRegistry.init();
		BlockRegistry.init();
		BlockEntityRegistry.init();
		ScreenHandlerRegistry.init();
		FlaskGroup.init();

		EnhancerLootInjections.parseLootTableConfig();

	}
}