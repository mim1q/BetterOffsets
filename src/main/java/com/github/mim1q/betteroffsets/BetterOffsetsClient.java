package com.github.mim1q.betteroffsets;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterOffsetsClient implements ClientModInitializer {
  public static final String MOD_ID = "betteroffsets";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  @Override
  public void onInitializeClient() {
    LOGGER.info("BetterOffsetsClient initialized");
  }

  public static Identifier id(String path) {
    return new Identifier(MOD_ID, path);
  }
}
