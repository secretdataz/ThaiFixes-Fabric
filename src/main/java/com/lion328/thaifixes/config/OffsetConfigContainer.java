package com.lion328.thaifixes.config;

import java.util.List;

public class OffsetConfigContainer {
    public int version;
    public List<OffsetConfig> offsets;

    public class OffsetConfig {
        public String characters;
        public TexturedGlyphOffsetConfig textured;
        public TrueTypeGlyphOffsetConfig trueType;
    }

    public class TexturedGlyphOffsetConfig {
        public float yOffset;
        public float heightOffset;
    }

    public class TrueTypeGlyphOffsetConfig {
        public float trueTypeXOffsetMultiplier;
        public float trueTypeYOffsetMultiplier;
    }
}
