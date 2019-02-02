package com.lion328.thaifixes.mixin;

import com.lion328.thaifixes.IThaiFixesTrueTypeGlyph;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = {"net.minecraft.client.font.TrueTypeFont$TtfGlyph"})
public abstract class MixinTtfGlyph implements IThaiFixesTrueTypeGlyph {

    private boolean thaiFixesFlag = false;
    private char aChar = '\0';
    private float bf_oversample = 0.0f;

    @Override
    public void setThaiFixesFlag(boolean flag) {
        thaiFixesFlag = flag;
    }

    @Override
    public boolean getThaiFixesFlag() {
        return thaiFixesFlag;
    }

    @Override
    public void setCharacter(char c) {
        aChar = c;
    }

    @Override
    public char getCharacter() {
        return aChar;
    }

    @Override
    public void setBfOversample(float value) {
        this.bf_oversample = value;
    }

    @Override
    public float getBfOversample() {
        return bf_oversample;
    }
}
