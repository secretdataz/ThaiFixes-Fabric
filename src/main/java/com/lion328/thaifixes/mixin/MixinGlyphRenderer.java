package com.lion328.thaifixes.mixin;

import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GlyphRenderer.class)
public interface MixinGlyphRenderer {
    @Accessor(value = "textLayer")
    RenderLayer getRenderLayer1();

    @Accessor(value = "seeThroughTextLayer")
    RenderLayer getRenderLayer2();

    @Accessor(value = "uMin")
    float thaiFixesUMin();

    @Accessor(value = "uMax")
    float thaiFixesUMax();

    @Accessor(value = "vMin")
    float thaiFixesVMin();

    @Accessor(value = "vMax")
    float thaiFixesVMax();

    @Accessor(value = "xMin")
    float thaiFixesXMin();

    @Accessor(value = "xMax")
    float thaiFixesXMax();

    @Accessor(value = "yMin")
    float thaiFixesYMin();

    @Accessor(value = "yMax")
    float thaiFixesYMax();
}
