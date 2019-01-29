package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IGlyphRenderer;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GlyphRenderer.class)
public class MixinGlyphRenderer implements IGlyphRenderer {

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
    public float UMin() {
        return uMin;
    }

    @Override
    public float UMax() {
        return uMax;
    }

    @Override
    public float VMin() {
        return vMin;
    }

    @Override
    public float VMax() {
        return vMax;
    }

    @Override
    public float XMin() {
        return xMin;
    }

    @Override
    public float XMax() {
        return xMax;
    }

    @Override
    public float YMin() {
        return yMin;
    }

    @Override
    public float YMax() {
        return yMax;
    }
}
