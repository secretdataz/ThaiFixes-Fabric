package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IGlyphRenderer;
import com.lion328.thaifixes.IVariableAdvanceGlyph;
import com.lion328.thaifixes.ThaiFixesMod;
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
        if(!ThaiFixesMod.processingThaiChars.containsKey(glyph)) {
            return;
        }

        GlyphRenderer glyphRenderer = ci.getReturnValue();
        IGlyphRenderer converted = (IGlyphRenderer)glyphRenderer;
        IVariableAdvanceGlyph advanceGlyph = (IVariableAdvanceGlyph)glyph;

        boolean hangBelow = ThaiFixesMod.processingThaiChars.get(glyph);

        float posYShift = 0.0f;
        float height = 2.99f;

        if (hangBelow)
        {
            posYShift = 6.0f;
            height = 1.99f;
        }

        GlyphRenderer newGlyphRenderer = new GlyphRenderer(glyphRenderer.getId(), converted.UMin() + posYShift / 128.0f, converted.UMin() + height / 128.0f, converted.VMin(), converted.VMax(), converted.XMin() - advanceGlyph.getRealAdvance(), converted.XMax() - advanceGlyph.getRealAdvance(), converted.YMin() + posYShift, converted.YMax() + height);
        ThaiFixesMod.processingThaiChars.remove(glyph);

        ci.setReturnValue(newGlyphRenderer);
    }
}
