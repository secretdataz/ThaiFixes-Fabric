package com.lion328.thaifixes;

public interface IThaiFixesTrueTypeGlyph {
    void setThaiFixesFlag(boolean flag);
    boolean getThaiFixesFlag();
    void setThaiFixesCharacter(char c);
    char getThaiFixesCharacter();
    void setThaiFixesOversample(float value);
    float getThaiFixesOversample();
}
