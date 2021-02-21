package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesTrueTypeGlyph;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = {"net.minecraft.client.font.TrueTypeFont$TtfGlyph"})
public abstract class MixinTtfGlyph implements IThaiFixesTrueTypeGlyph {

    private boolean thaiFixesFlag = false;
    private int thaiFixesCharacter = '\0';
    private float thaiFixesOversample = 0.0f;

    @Override
    public void setThaiFixesFlag(boolean flag) {
        thaiFixesFlag = flag;
    }

    @Override
    public boolean getThaiFixesFlag() {
        return thaiFixesFlag;
    }

    @Override
    public void setThaiFixesCharacter(int i) {
        thaiFixesCharacter = i;
    }

    @Override
    public int getThaiFixesCharacter() {
        return thaiFixesCharacter;
    }

    @Override
    public void setThaiFixesOversample(float value) {
        this.thaiFixesOversample = value;
    }

    @Override
    public float getThaiFixesOversample() {
        return thaiFixesOversample;
    }
}
