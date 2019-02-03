package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.*;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.client.gui.fonts.IGlyphInfo;
import net.minecraft.client.gui.fonts.TexturedGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Font.class)
public abstract class MixinFontStorage {

    @Inject(method= "createTexturedGlyph", at = @At("RETURN"), cancellable = true)
    private void onCreateTexturedGlyph(IGlyphInfo glyph, CallbackInfoReturnable<TexturedGlyph> ci) {
        TexturedGlyph result = GlyphProcessor.processGlyph(glyph, ci.getReturnValue());
        if(result != null)
            ci.setReturnValue(result);
    }
}
