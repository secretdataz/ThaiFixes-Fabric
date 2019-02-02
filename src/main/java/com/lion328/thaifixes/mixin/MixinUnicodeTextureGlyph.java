package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesUnicodeGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.client.font.UnicodeTextureFont$UnicodeTextureGlyph"}, priority = 500)
public abstract class MixinUnicodeTextureGlyph implements IThaiFixesUnicodeGlyph {

    private boolean thaiFixesShouldRemoveAdvance = false;
    private boolean thaiFixesFlag = false;
    private char thaiFixesCharacter;

    @Shadow
    public int width;

    @Inject(method="getAdvance()F", at=@At("HEAD"), cancellable = true, remap = false)
    public void getAdvance(CallbackInfoReturnable<Float> ci) {
        if(thaiFixesShouldRemoveAdvance) {
            ci.setReturnValue(0.0f);
        }
    }

    public float getRealAdvance() {
        return (float)(width / 2 + 1);
    }

    @Override
    public boolean shouldRemoveAdvance() {
        return thaiFixesShouldRemoveAdvance;
    }

    @Override
    public void setRemoveAdvance(boolean flag) {
        thaiFixesShouldRemoveAdvance = flag;
    }

    @Override
    public boolean getThaiFixesFlag() {
        return thaiFixesFlag;
    }

    @Override
    public void setThaiFixesFlag(boolean flag) {
        thaiFixesFlag = flag;
    }

    @Override
    public char getThaiFixesCharacter() {
        return thaiFixesCharacter;
    }

    @Override
    public void setThaiFixesCharacter(char thaiFixesCharacter) {
        this.thaiFixesCharacter = thaiFixesCharacter;
    }
}
