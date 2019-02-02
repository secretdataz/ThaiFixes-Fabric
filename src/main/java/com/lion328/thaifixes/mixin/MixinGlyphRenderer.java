package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IGlyphRenderer;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GlyphRenderer.class)
public abstract class MixinGlyphRenderer implements IGlyphRenderer {

    @Shadow
    private Identifier id;

    @Shadow
    private float uMin;

    @Shadow
    private float uMax;

    @Shadow
    private float vMin;

    @Shadow
    private float vMax;

    @Shadow
    private float xMin;

    @Shadow
    private float xMax;

    @Shadow
    private float yMin;

    @Shadow
    private float yMax;

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public float thaiFixesUMin() {
        return uMin;
    }

    @Override
    public float thaiFixesUMax() {
        return uMax;
    }

    @Override
    public float thaiFixesVMin() {
        return vMin;
    }

    @Override
    public float thaiFixesVMax() {
        return vMax;
    }

    @Override
    public float thaiFixesXMin() {
        return xMin;
    }

    @Override
    public float thaiFixesXMax() {
        return xMax;
    }

    @Override
    public float thaiFixesYMin() {
        return yMin;
    }

    @Override
    public float thaiFixesYMax() {
        return yMax;
    }
}
