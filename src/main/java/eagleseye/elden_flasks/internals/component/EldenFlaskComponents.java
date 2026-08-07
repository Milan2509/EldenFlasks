package eagleseye.elden_flasks.internals.component;

import com.mojang.serialization.Codec;
import eagleseye.elden_flasks.EldenFlasks;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class EldenFlaskComponents {
    public static final ComponentType<Integer> FLASK_MAX_USES = registerComponent("flask_max_uses", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Integer> FLASK_CURRENT_USES = registerComponent("flask_current_uses", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Float> HEAL_AMOUNT = registerComponent("heal_amount", builder -> builder.codec(Codec.FLOAT));
    public static final ComponentType<Integer> RECHARGE_KILL_REQUIREMENT = registerComponent("recharge_kill_requirement", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Integer> RECHARGE_KILL_CURRENT = registerComponent("recharge_kill_current", builder -> builder.codec(Codec.INT));


    private static <T> ComponentType<T> registerComponent(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(EldenFlasks.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }
}
