package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IVariableAdvanceGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.client.font.UnicodeTextureFont$UnicodeTextureGlyph"}, priority = 500)
public abstract class MixinUnicodeTextureGlyph implements IVariableAdvanceGlyph {

    private boolean shouldRemoveAdvance = false;

    @Shadow
    public abstract int getWidth();

    @Inject(method="Lnet/minecraft/client/font/UnicodeTextureFont$UnicodeTextureGlyph;getAdvance()F", at=@At("HEAD"), cancellable = true)
    public void getAdvance(CallbackInfoReturnable<Float> ci) {
        if(shouldRemoveAdvance) {
            ci.setReturnValue(0.0f);
        }
    }

    public float getRealAdvance() {
        return (float)(getWidth() / 2 + 1);
    }

    @Override
    public boolean shouldRemoveAdvance() {
        return shouldRemoveAdvance;
    }

    @Override
    public void setRemoveAdvance(boolean flag) {
        shouldRemoveAdvance = flag;
    }
}