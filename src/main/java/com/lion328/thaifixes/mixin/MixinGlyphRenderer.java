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

    @Accessor(value = "polygonOffsetTextLayer")
    RenderLayer getRenderLayer3();

    @Accessor(value = "minU")
    float thaiFixesUMin();

    @Accessor(value = "maxU")
    float thaiFixesUMax();

    @Accessor(value = "minV")
    float thaiFixesVMin();

    @Accessor(value = "maxV")
    float thaiFixesVMax();

    @Accessor(value = "minX")
    float thaiFixesXMin();

    @Accessor(value = "maxX")
    float thaiFixesXMax();

    @Accessor(value = "minY")
    float thaiFixesYMin();

    @Accessor(value = "maxY")
    float thaiFixesYMax();
}
