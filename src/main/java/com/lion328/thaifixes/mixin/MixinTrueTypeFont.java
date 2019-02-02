package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesTrueTypeGlyph;
import com.lion328.thaifixes.ThaiUtil;
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

    @Inject(method = "method_2051", at = @At("RETURN"), cancellable = true) // remap?
    private void onGetGlyph(char c, CallbackInfoReturnable<RenderableGlyph> ci) {
        if(!ThaiUtil.isEligibleForTrueTypeCorrection(c)) {
            return;
        }

        IThaiFixesTrueTypeGlyph glyph = (IThaiFixesTrueTypeGlyph) ci.getReturnValue();
        glyph.setThaiFixesFlag(true);
        glyph.setCharacter(c);
        glyph.setBfOversample(oversample);
    }
}
