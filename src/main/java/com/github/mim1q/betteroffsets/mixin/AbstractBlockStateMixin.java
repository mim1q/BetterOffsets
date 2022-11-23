package com.github.mim1q.betteroffsets.mixin;

import com.github.mim1q.betteroffsets.block.BetterOffsetsTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
  @Shadow public abstract Block getBlock();

  @Shadow public abstract boolean isIn(TagKey<Block> tag);

  @Shadow @Final private AbstractBlock.OffsetType offsetType;

  @Inject(method = "getModelOffset(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;", at = @At("HEAD"), cancellable = true)
  private void getModelOffset(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> cir) {
    if (this.isIn(BetterOffsetsTags.VERTICAL_VARIATION)) {
      Block block = this.getBlock();
      long seed = MathHelper.hashCode(pos.getX(), pos.getY(), pos.getZ());
      float f = block.getMaxHorizontalModelOffset();
      double d = MathHelper.clamp(((double)((float)(seed & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
      double e = this.offsetType == AbstractBlock.OffsetType.XYZ ? ((double)((float)(seed >> 4 & 15L) / 15.0F) - 1.0) * (double)block.getVerticalModelOffsetMultiplier() : 0.0;
      double g = MathHelper.clamp(((double)((float)(seed >> 8 & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
      cir.setReturnValue(new Vec3d(d, e, g));
    }
  }
}
