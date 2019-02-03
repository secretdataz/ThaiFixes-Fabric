package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IGlyphRenderer;
import net.minecraft.client.gui.fonts.TexturedGlyph;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TexturedGlyph.class)
public abstract class MixinGlyphRenderer implements IGlyphRenderer {

    @Shadow
    private ResourceLocation textureLocation;

    @Shadow
    private float u0;

    @Shadow
    private float u1;

    @Shadow
    private float v0;

    @Shadow
    private float v1;

    @Shadow
    private float field_211240_f;

    @Shadow
    private float field_211241_g;

    @Shadow
    private float field_211242_h;

    @Shadow
    private float field_211243_i;

    @Override
    public ResourceLocation getThaiFixesIdentifier() {
        return textureLocation;
    }

    @Override
    public float thaiFixesUMin() {
        return u0;
    }

    @Override
    public float thaiFixesUMax() {
        return u1;
    }

    @Override
    public float thaiFixesVMin() {
        return v0;
    }

    @Override
    public float thaiFixesVMax() {
        return v1;
    }

    @Override
    public float thaiFixesXMin() {
        return field_211240_f;
    }

    @Override
    public float thaiFixesXMax() {
        return field_211241_g;
    }

    @Override
    public float thaiFixesYMin() {
        return field_211242_h;
    }

    @Override
    public float thaiFixesYMax() {
        return field_211243_i;
    }
}
