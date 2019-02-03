package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesTrueTypeGlyph;
import com.lion328.thaifixes.ThaiUtil;
import net.minecraft.client.gui.fonts.IGlyphInfo;
import net.minecraft.client.gui.fonts.providers.TrueTypeGlyphProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrueTypeGlyphProvider.class)
public abstract class MixinTrueTypeFont {

    @Shadow
    private float oversample;

    @Inject(method = "func_212248_a", at = @At("RETURN"), cancellable = true) // remap?
    private void onGetGlyph(char c, CallbackInfoReturnable<IGlyphInfo> ci) {
        if(!ThaiUtil.isEligibleForTrueTypeCorrection(c)) {
            return;
        }

        IThaiFixesTrueTypeGlyph glyph = (IThaiFixesTrueTypeGlyph) ci.getReturnValue();
        glyph.setThaiFixesFlag(true);
        glyph.setThaiFixesCharacter(c);
        glyph.setThaiFixesOversample(oversample);
    }
}
