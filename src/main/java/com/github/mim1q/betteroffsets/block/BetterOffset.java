package com.github.mim1q.betteroffsets.block;

import net.minecraft.block.BlockState;

public class BetterOffset {
  public final Type type;
  public final BetterOffsetFunction offsetFunction;

  public BetterOffset(Type type) {
    this.type = type;
    this.offsetFunction = null;
  }

  public BetterOffset(BetterOffsetFunction offsetFunction) {
    this.type = null;
    this.offsetFunction = offsetFunction;
  }

  public Type getType(BlockState state) {
    if (offsetFunction != null) {
      return offsetFunction.getType(state);
    }
    return type;
  }

  public enum Type {

    NONE(false, false, false),
    X(true, false, false),
    Y(false, true, false),
    Z(false, false, true),
    XY(true, true, false),
    XZ(true, false, true),
    YZ(false, true, true),
    XYZ(true, true, true);

    private final boolean hasXOffset;
    private final boolean hasYOffset;
    private final boolean hasZOffset;

    Type(boolean hasXOffset, boolean hasYOffset, boolean hasZOffset) {
      this.hasXOffset = hasXOffset;
      this.hasYOffset = hasYOffset;
      this.hasZOffset = hasZOffset;
    }

    public boolean hasXOffset() {
      return hasXOffset;
    }

    public boolean hasYOffset() {
      return hasYOffset;
    }

    public boolean hasZOffset() {
      return hasZOffset;
    }
  }

  @FunctionalInterface
  public interface BetterOffsetFunction {
    Type getType(BlockState state);
  }
}
