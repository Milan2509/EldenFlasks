package eagleseye.elden_flasks.internals.item.custom;

import net.minecraft.text.Text;
import net.minecraft.util.Language;

public enum EnhancerType {
    USES("enhancer_type.uses"),
    HEALING("enhancer_type.healing");

    public final String id;
    EnhancerType(String id){
        this.id = id;
    }

    public String getDisplayName(){
        return Language.getInstance().get(id);
    }
}
