package hibi.blahaj.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import hibi.blahaj.block.*;

public class CuddlyBlockEntity extends BlockEntity {

    

    public CuddlyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        PolymerBlockUtils.registerBlockEntity(type);
    }
    
}
