package eagleseye.eldenflasks.buff;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BuffManager {
    public static Map<Identifier, FlaskBuff> buffMap = new HashMap<>();

    public static void registerBuff(Identifier id, FlaskBuff buff){
        buffMap.put(id, buff);
    }

    public static FlaskBuff getBuff(String name){
        String modId = StringUtils.substringBefore(name, ":");
        String buffName = StringUtils.substringAfter(name, ":");
        Identifier id = new Identifier(modId, buffName);
        if (!buffMap.containsKey(id)) return null;
        return buffMap.get(id);
    }

    public static boolean buffExists(String name){
        String modId = StringUtils.substringBefore(name, ":");
        String buffName = StringUtils.substringAfter(name, ":");

        Identifier id = new Identifier(modId, buffName);
        return buffMap.containsKey(id);
    }

    public static void sendBuffMapToPlayer(ServerPlayerEntity serverPlayer){
        serverPlayer.sendMessage(Text.literal(buffMap.toString()));
    }

    public static void init(){}
}
