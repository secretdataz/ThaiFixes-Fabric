package com.lion328.thaifixes;

public interface IThaiFixesTrueTypeGlyph {
    void setThaiFixesFlag(boolean flag);
    boolean getThaiFixesFlag();
    void setThaiFixesCharacter(int i);
    int getThaiFixesCharacter();
    void setThaiFixesOversample(float value);
    float getThaiFixesOversample();
}
