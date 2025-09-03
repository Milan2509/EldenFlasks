package eagleseye.eldenflasks.buff;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class BuffManager {
    public static Map<Identifier, FlaskBuff> buffMap = new HashMap<>();

    public static void registerBuff(Identifier id, FlaskBuff buff){
        buffMap.put(id, buff);
    }

    public static FlaskBuff getBuff(String name){
        Identifier id = new Identifier(name);
        if (!buffMap.containsKey(id)) return null;
        return buffMap.get(id);
    }

    public static boolean buffExists(String name){
        Identifier id = new Identifier(name);
        return buffMap.containsKey(id);
    }

    public static void init(){}
}
