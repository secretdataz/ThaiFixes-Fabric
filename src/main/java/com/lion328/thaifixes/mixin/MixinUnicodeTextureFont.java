package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IVariableAdvanceGlyph;
import com.lion328.thaifixes.ThaiFixesMod;
import com.lion328.thaifixes.ThaiUtil;
import net.minecraft.client.font.RenderableGlyph;
import net.minecraft.client.font.UnicodeTextureFont;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UnicodeTextureFont.class)
public class MixinUnicodeTextureFont {

    @Inject(method="getGlyph", at = @At("RETURN"), cancellable = true)
    private void getGlyph(char c, CallbackInfoReturnable<RenderableGlyph> ci) {
        if(!ThaiUtil.isHangingThaiChar(c)) {
            return;
        }

        IVariableAdvanceGlyph glyph = (IVariableAdvanceGlyph)ci.getReturnValue();
        glyph.setRemoveAdvance(true);

        ThaiFixesMod.processingThaiChars.put(ci.getReturnValue(), ThaiUtil.isHangingBelowThaiChar(c));
    }
}
