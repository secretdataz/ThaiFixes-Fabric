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

        boolean hangBelow = ThaiUtil.isHangingBelowThaiChar(thaiFixesGlyph.getCharacter());

        float posYShift = 0.0f;
        float height = 2.99f;

        if (hangBelow) {
            posYShift = 6.0f;
            height = 1.99f;
        }

        float v0 = converted.VMin() + posYShift / 128.0f;
        float y0 = converted.YMin() + posYShift;
        return new GlyphRenderer(originalReturnValue.getId(),
                converted.UMin(),
                converted.UMax(),
                v0,
                v0 + height / 128.0f,
                converted.XMin() - thaiFixesGlyph.getRealAdvance(),
                converted.XMax() - thaiFixesGlyph.getRealAdvance(),
                y0,
                y0 + height);
    }

    private static GlyphRenderer processTrueTypeGlyph(RenderableGlyph glyph, GlyphRenderer originalReturnValue) {
        IThaiFixesTrueTypeGlyph thaiFixesGlyph = (IThaiFixesTrueTypeGlyph)glyph;

        if(!thaiFixesGlyph.getThaiFixesFlag())
            return null;

        IGlyphRenderer converted = (IGlyphRenderer)originalReturnValue;

        float x_offset = glyph.getWidth() / thaiFixesGlyph.getBfOversample() * 1.22f;
        float y_offset = ThaiUtil.isHangingAllAboveThaiChar(thaiFixesGlyph.getCharacter()) ? glyph.getHeight() / thaiFixesGlyph.getBfOversample() : 0.0f;

        if(thaiFixesGlyph.getCharacter() == ThaiUtil.SARA_UM) {
            x_offset = glyph.getWidth() * 0.1f;
        }

        return new GlyphRenderer(originalReturnValue.getId(),
                converted.UMin(),
                converted.UMax(),
                converted.VMin(),
                converted.VMax(),
                converted.XMin() + x_offset,
                converted.XMax() + x_offset,
                converted.YMin() - y_offset,
                converted.YMax() - y_offset);
    }
}
