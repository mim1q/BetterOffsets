package com.github.mim1q.betteroffsets.block;

import com.github.mim1q.betteroffsets.BetterOffsetsClient;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class BetterOffsetsTags {
  public static final TagKey<Block> VERTICAL_VARIATION = TagKey.of(Registry.BLOCK_KEY, BetterOffsetsClient.id("vertical_variation"));
}
