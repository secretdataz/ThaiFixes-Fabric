package com.lion328.thaifixes;

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

        IGlyphRenderer converted = (IGlyphRenderer) originalReturnValue;

        boolean hangBelow = ThaiUtil.isHangingBelowThaiChar(thaiFixesGlyph.getThaiFixesCharacter());

        float posYShift = 0.0f;
        float height = 2.99f;

        if (hangBelow) {
            posYShift = 6.0f;
            height = 1.99f;
        }

        float v0 = converted.thaiFixesVMin() + posYShift / 128.0f;
        float y0 = converted.thaiFixesYMin() + posYShift;
        return new GlyphRenderer(originalReturnValue.getId(),
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

        IGlyphRenderer converted = (IGlyphRenderer)originalReturnValue;

        float x_offset = glyph.getWidth() / thaiFixesGlyph.getThaiFixesOversample() * 1.22f;
        float y_offset = ThaiUtil.isHangingAllAboveThaiChar(thaiFixesGlyph.getThaiFixesCharacter()) ? glyph.getHeight() / thaiFixesGlyph.getThaiFixesOversample() : 0.0f;

        if(thaiFixesGlyph.getThaiFixesCharacter() == ThaiUtil.SARA_UM) {
            x_offset = glyph.getWidth() * 0.1f;
        }

        return new GlyphRenderer(originalReturnValue.getId(),
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
