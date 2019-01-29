package com.lion328.thaifixes;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.font.RenderableGlyph;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ThaiFixesMod implements ModInitializer {

	private static Logger logger;
	public static Map<RenderableGlyph, Boolean> processingThaiChars = new ConcurrentHashMap<>();

	@Override
	public void onInitialize() {

	}

	public static Logger getLogger() {
		return logger;
	}
}
