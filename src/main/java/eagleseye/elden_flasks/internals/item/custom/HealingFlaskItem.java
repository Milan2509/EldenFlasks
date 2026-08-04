package eagleseye.elden_flasks.internals.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HealingFlaskItem extends Item {
    public HealingFlaskItem() {
        super(new Item.Settings()
                .rarity(Rarity.RARE)
                .maxCount(1)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.sendMessage(Text.literal("use"));
        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.sendMessage(Text.literal("finishUsing"));
        return super.finishUsing(stack, world, user);
    }
}
