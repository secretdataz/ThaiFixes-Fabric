package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesTrueTypeGlyph;
import com.lion328.thaifixes.ThaiFixesMod;
import net.minecraft.client.font.RenderableGlyph;
import net.minecraft.client.font.TrueTypeFont;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrueTypeFont.class)
public abstract class MixinTrueTypeFont {

    @Shadow
    private float oversample;

    @Inject(method = "method_2051", at = @At("RETURN"), cancellable = true)
    private void onGetGlyph(char c, CallbackInfoReturnable<RenderableGlyph> ci) {
        if(ThaiFixesMod.trueTypeGlyphOffsetMap.get(c) == null) {
            return;
        }

        IThaiFixesTrueTypeGlyph glyph = (IThaiFixesTrueTypeGlyph) ci.getReturnValue();
        glyph.setThaiFixesFlag(true);
        glyph.setThaiFixesCharacter(c);
        glyph.setThaiFixesOversample(oversample);
    }
}
