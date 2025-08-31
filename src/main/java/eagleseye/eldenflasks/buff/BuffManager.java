package eagleseye.eldenflasks.buff;

import eagleseye.eldenflasks.EldenFlasks;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.util.Identifier;

public class BuffManager {
    public static final RegistryKey<Registry<FlaskBuff>> FLASK_BUFF_KEY =
            RegistryKey.ofRegistry(new Identifier(EldenFlasks.MOD_ID, "flask_buffs"));

    public static void init(){
//        DynamicRegistries.register(FLASK_BUFF_KEY, FlaskBuff.CODEC);
    }
}
