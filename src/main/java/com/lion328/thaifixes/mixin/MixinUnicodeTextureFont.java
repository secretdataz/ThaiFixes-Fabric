package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesUnicodeGlyph;
import com.lion328.thaifixes.ThaiUtil;
import net.minecraft.client.gui.fonts.IGlyphInfo;
import net.minecraft.client.gui.fonts.providers.TextureGlyphProviderUnicode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextureGlyphProviderUnicode.class)
public abstract class MixinUnicodeTextureFont {

    @Inject(method="func_212248_a", at = @At("RETURN"), cancellable = true)
    private void getGlyph(char c, CallbackInfoReturnable<IGlyphInfo> ci) {
        if(!ThaiUtil.isHangingThaiChar(c)) {
            return;
        }

        IThaiFixesUnicodeGlyph glyph = (IThaiFixesUnicodeGlyph)ci.getReturnValue();
        glyph.setRemoveAdvance(true);
        glyph.setThaiFixesFlag(true);
        glyph.setThaiFixesCharacter(c);
    }
}
