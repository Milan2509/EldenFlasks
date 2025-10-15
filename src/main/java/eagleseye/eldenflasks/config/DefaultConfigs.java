package eagleseye.eldenflasks.config;

import java.util.List;

import static eagleseye.eldenflasks.EldenFlasks.FLASKS_CONFIG;

public class DefaultConfigs {
    private static void setFullyRechargeEntities(){
        if(FLASKS_CONFIG.firstLoad()) {
            List<String> rechargeList = FLASKS_CONFIG.fullyRechargeEntities();
            rechargeList.add("minecraft:wither");
            rechargeList.add("minecraft:ender_dragon");
            rechargeList.add("minecells:concierge");
            rechargeList.add("minecraft:conjunctivius");
            rechargeList.add("bosses_of_mass_destruction:lich");
            rechargeList.add("bosses_of_mass_destruction:gauntlet");
            rechargeList.add("bosses_of_mass_destruction:void_blossom");
            rechargeList.add("cataclysm:ignis");
            rechargeList.add("cataclysm:ender_golem");
            rechargeList.add("cataclysm:netherite_monstrosity");
            rechargeList.add("cataclysm:leviathan");
            rechargeList.add("cataclysm:ancient_remnant");
            rechargeList.add("cataclysm:harbinger");
            rechargeList.add("cataclysm:scylla");
            
            FLASKS_CONFIG.firstLoad(false);
        }
    }

    public static void init(){
        setFullyRechargeEntities();
    }
}
