package com.lion328.thaifixes;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThaiFixesMod implements ModInitializer {

	private static Logger logger = LogManager.getLogger("ThaiFixes");

	@Override
	public void onInitialize() {
		getLogger().info("ThaiFixes is initialized.");
	}

	public static Logger getLogger() {
		return logger;
	}
}
