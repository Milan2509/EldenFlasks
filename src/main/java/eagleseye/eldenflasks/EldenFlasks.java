package eagleseye.eldenflasks;

import eagleseye.eldenflasks.buff.BuffEffect;
import eagleseye.eldenflasks.buff.BuffManager;
import eagleseye.eldenflasks.registry.*;
import eagleseye.eldenflasks.config.EldenFlasksFlaskConfig;
import eagleseye.eldenflasks.config.EldenFlasksLootConfig;
import eagleseye.eldenflasks.util.EnhancerLootInjections;
import eagleseye.incombat.api.CombatCheck;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EldenFlasks implements ModInitializer {
	public static final String MOD_ID = "eldenflasks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EldenFlasksFlaskConfig FLASKS_CONFIG = EldenFlasksFlaskConfig.createAndLoad();
	public static final EldenFlasksLootConfig LOOT_CONFIG = EldenFlasksLootConfig.createAndLoad();

	//Buff effect
	public static final StatusEffect BUFFED_EFFECT = new BuffEffect();

	// Flask Helper Function
	public static boolean canRechargeFlask(PlayerEntity player){
		if (!FabricLoader.getInstance().isModLoaded("incombat")) return true;

		else return !CombatCheck.isPlayerInCombat(player);
	}

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

		//Defaults
		if(FLASKS_CONFIG.firstLoad()) {
			List<String> rechargeList = FLASKS_CONFIG.fullyRechargeEntities();
			rechargeList.add("minecraft:wither");
			rechargeList.add("minecraft:ender_dragon");
			rechargeList.add("minecells:concierge");
			rechargeList.add("minecraft:conjunctivius");
			rechargeList.add("bosses_of_mass_destruction:lich");
			rechargeList.add("bosses_of_mass_destruction:gauntlet");
			rechargeList.add("bosses_of_mass_destruction:void_blossom");
			FLASKS_CONFIG.firstLoad(false);
		}

		LOGGER.info("Successfully Initialized: " + MOD_ID);
	}
}