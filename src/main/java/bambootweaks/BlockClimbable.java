package bambootweaks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

public interface BlockClimbable {
    default boolean canClimb(LivingEntity entity_1, BlockState state_1, BlockPos pos_1) {
        return true;
    }
}