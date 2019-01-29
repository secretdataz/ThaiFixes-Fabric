package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IGlyphRenderer;
import com.lion328.thaifixes.IThaiFixesGlyph;
import com.lion328.thaifixes.ThaiFixesMod;
import com.lion328.thaifixes.ThaiUtil;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.font.RenderableGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FontStorage.class)
public class MixinFontStorage {

    @Inject(method="Lnet/minecraft/client/font/FontStorage;getGlyphRenderer(Lnet/minecraft/client/font/RenderableGlyph;)Lnet/minecraft/client/font/GlyphRenderer;", at = @At("RETURN"), cancellable = true)
    private void getGlyphRenderer(RenderableGlyph glyph, CallbackInfoReturnable<GlyphRenderer> ci) {
        if(!(glyph instanceof IThaiFixesGlyph))
            return;

        IThaiFixesGlyph thaiFixesGlyph = (IThaiFixesGlyph)glyph;

        if(!thaiFixesGlyph.getThaiFixesFlag())
            return;

        GlyphRenderer glyphRenderer = ci.getReturnValue();
        IGlyphRenderer converted = (IGlyphRenderer)glyphRenderer;


        boolean hangBelow = ThaiUtil.isHangingBelowThaiChar(thaiFixesGlyph.getCharacter());

        float posYShift = 0.0f;
        float height = 2.99f;

        if (hangBelow)
        {
            posYShift = 6.0f;
            height = 1.99f;
        }

        float v0 = converted.VMin() + posYShift / 128.0f;
        float y0 = converted.YMin() + posYShift;
        GlyphRenderer newGlyphRenderer = new GlyphRenderer(glyphRenderer.getId(),
                converted.UMin(),
                converted.UMax(),
                v0,
                v0 + height / 128.0f,
                converted.XMin() - thaiFixesGlyph.getRealAdvance(),
                converted.XMax() - thaiFixesGlyph.getRealAdvance(),
                y0,
                y0 + height);

        ci.setReturnValue(newGlyphRenderer);
    }
}
