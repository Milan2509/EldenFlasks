package eagleseye.eldenflasks;

import eagleseye.eldenflasks.buff.BuffEffect;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.registry.*;
import eagleseye.eldenflasks.config.EldenFlasksFlaskConfig;
import eagleseye.eldenflasks.config.EldenFlasksLootConfig;
import eagleseye.eldenflasks.util.EnhancerLootInjections;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EldenFlasks implements ModInitializer {
	public static final String MOD_ID = "eldenflasks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EldenFlasksFlaskConfig FLASKS_CONFIG = EldenFlasksFlaskConfig.createAndLoad();
	public static final EldenFlasksLootConfig LOOT_CONFIG = EldenFlasksLootConfig.createAndLoad();

	//Buff effect
	public static final StatusEffect BUFFED_EFFECT = new BuffEffect();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing: " + MOD_ID);
		//Register Buff Effect
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "buffed"), BUFFED_EFFECT);

		//Other class initializations
		BuffManager.init();

		//Registry initializations
		BuffRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		BlockEntityRegistry.init();
		ScreenHandlerRegistry.init();
		FlaskGroup.init();

		//Config
		EnhancerLootInjections.parseLootTableConfig();

		System.out.println(BuffManager.buffMap);

		LOGGER.info("Successfully Initialized: " + MOD_ID);
	}
}