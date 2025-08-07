package eagleseye.eldenflasks.block.entity;

import eagleseye.eldenflasks.EldenFlasks;
import eagleseye.eldenflasks.block.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegistry {
    public static final BlockEntityType<FlaskMixerBlockEntity> FLASK_MIXER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(EldenFlasks.MOD_ID, "flask_mixer_be"),
                    FabricBlockEntityTypeBuilder.create(FlaskMixerBlockEntity::new, BlockRegistry.MIXER).build());

    public static void init(){}
}
