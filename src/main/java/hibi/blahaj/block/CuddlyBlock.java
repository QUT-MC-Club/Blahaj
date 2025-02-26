package hibi.blahaj.block;

import com.mojang.serialization.*;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import eu.pb4.polymer.core.api.block.*;
import eu.pb4.polymer.core.api.item.*;
import net.minecraft.world.*;
import xyz.nucleoid.packettweaker.PacketContext;

public class CuddlyBlock extends HorizontalFacingBlock implements PolymerBlock {

	protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
	public static final MapCodec<CuddlyBlock> CODEC = createCodec(CuddlyBlock::new);

	public CuddlyBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
		return CODEC;
	}


	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		//world.playSound(null, hit.getBlockPos(), BlahajSoundEvents.BLOCK_CUDDLY_ITEM_HIT, SoundCategory.BLOCKS, 0.5f, 1);
		super.onProjectileHit(world, state, hit, projectile);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		//world.playSound(null, pos, BlahajSoundEvents.getRandomSqueak(world.getRandom()), SoundCategory.BLOCKS, 0.5f, 1);
		return ActionResult.SUCCESS;
	}

	@Override
	public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
		return this.getDefaultState();
	}
}
