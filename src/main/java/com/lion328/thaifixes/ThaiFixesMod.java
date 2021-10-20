package com.lion328.thaifixes;

import com.google.gson.*;
import com.lion328.thaifixes.config.OffsetConfigContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ThaiFixesMod implements ClientModInitializer {

    private static final int OFFSETS_CONFIG_VERSION = 1;
	private static final Logger logger = LogManager.getLogger("ThaiFixes");

	public static Map<Integer, OffsetConfigContainer.TexturedGlyphOffsetConfig> texturedGlyphOffsetMap = new HashMap<>();
    public static Map<Integer, OffsetConfigContainer.TrueTypeGlyphOffsetConfig> trueTypeGlyphOffsetMap = new HashMap<>();

	@Override
	public void onInitializeClient() {
	    getLogger().info("Registering resource reload listener.");
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return new Identifier("thaifixes:offsets");
			}

			@Override
			public void reload(ResourceManager resourceManager) {
                getLogger().info("Reloading ThaiFixes offset configurations.");
                Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
                resourceManager.findResources("offsets", s -> s.endsWith(".json")).forEach(e -> {
                    if (!e.getNamespace().equals("thaifixes")) return;
                    try {
                        resourceManager.getAllResources(e).forEach(res -> {
                            try {
                                getLogger().info("Reading ThaiFixes offset configuration file " + e.getNamespace() + ":" + e.getPath());
                                InputStream is = res.getInputStream();
                                OffsetConfigContainer container = JsonHelper.deserialize(gson, IOUtils.toString(is, StandardCharsets.UTF_8), OffsetConfigContainer.class);
                                if(container.version != OFFSETS_CONFIG_VERSION) {
                                    getLogger().warn("Found outdated ThaiFixes offset configuration file " + e.getNamespace() + ":" + e.getPath());
                                    return;
                                }
                                container.offsets.forEach(offset -> {
                                    for (char c : offset.characters.toCharArray()) {
                                        if (offset.textured != null) {
                                            texturedGlyphOffsetMap.put((int)c, offset.textured);
                                        }
                                        if (offset.trueType != null) {
                                            trueTypeGlyphOffsetMap.put((int)c, offset.trueType);
                                        }
                                    }
                                });
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (JsonParseException jsonEx) {
                                jsonEx.printStackTrace();
                            }
                        });
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                getLogger().info("ThaiFixes is done reloading offset config.");
			}
		});
	}

	public static Logger getLogger() {
		return logger;
	}
}
