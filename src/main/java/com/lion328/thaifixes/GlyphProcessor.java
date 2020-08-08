package com.lion328.thaifixes;

import com.lion328.thaifixes.mixin.MixinGlyphRenderer;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.font.RenderableGlyph;

public class GlyphProcessor {

    public static GlyphRenderer processGlyph(RenderableGlyph glyph, GlyphRenderer gr) {
        if(glyph instanceof IThaiFixesUnicodeGlyph) {
            return processUnicodeTextureGlyph(glyph, gr);
        }
        else if(glyph instanceof IThaiFixesTrueTypeGlyph) {
            return processTrueTypeGlyph(glyph, gr);
        }
        else {
            return null;
        }
    }

    private static GlyphRenderer processUnicodeTextureGlyph(RenderableGlyph glyph, GlyphRenderer originalReturnValue) {
        IThaiFixesUnicodeGlyph thaiFixesGlyph = (IThaiFixesUnicodeGlyph) glyph;

        if (!thaiFixesGlyph.getThaiFixesFlag())
            return null;

        MixinGlyphRenderer converted = (MixinGlyphRenderer) originalReturnValue;

        float posYShift = ThaiFixesMod.texturedGlyphOffsetMap.get(thaiFixesGlyph.getThaiFixesCharacter()).yOffset;
        float height = ThaiFixesMod.texturedGlyphOffsetMap.get(thaiFixesGlyph.getThaiFixesCharacter()).heightOffset;

        float v0 = converted.thaiFixesVMin() + posYShift / 128.0f;
        float y0 = converted.thaiFixesYMin() + posYShift;
        return new GlyphRenderer(
                converted.getRenderLayer1(),
                converted.getRenderLayer2(),
                converted.thaiFixesUMin(),
                converted.thaiFixesUMax(),
                v0,
                v0 + height / 128.0f,
                converted.thaiFixesXMin() - thaiFixesGlyph.getRealAdvance(),
                converted.thaiFixesXMax() - thaiFixesGlyph.getRealAdvance(),
                y0,
                y0 + height);
    }

    private static GlyphRenderer processTrueTypeGlyph(RenderableGlyph glyph, GlyphRenderer originalReturnValue) {
        IThaiFixesTrueTypeGlyph thaiFixesGlyph = (IThaiFixesTrueTypeGlyph)glyph;

        if(!thaiFixesGlyph.getThaiFixesFlag())
            return null;

        MixinGlyphRenderer converted = (MixinGlyphRenderer)originalReturnValue;

        float x_offset = glyph.getWidth() / thaiFixesGlyph.getThaiFixesOversample() * ThaiFixesMod.trueTypeGlyphOffsetMap.get(thaiFixesGlyph.getThaiFixesCharacter()).trueTypeXOffsetMultiplier;
        float y_offset = glyph.getHeight() / thaiFixesGlyph.getThaiFixesOversample() * ThaiFixesMod.trueTypeGlyphOffsetMap.get(thaiFixesGlyph.getThaiFixesCharacter()).trueTypeYOffsetMultiplier;

        return new GlyphRenderer(
                converted.getRenderLayer1(),
                converted.getRenderLayer2(),
                converted.thaiFixesUMin(),
                converted.thaiFixesUMax(),
                converted.thaiFixesVMin(),
                converted.thaiFixesVMax(),
                converted.thaiFixesXMin() + x_offset,
                converted.thaiFixesXMax() + x_offset,
                converted.thaiFixesYMin() - y_offset,
                converted.thaiFixesYMax() - y_offset);
    }
}
