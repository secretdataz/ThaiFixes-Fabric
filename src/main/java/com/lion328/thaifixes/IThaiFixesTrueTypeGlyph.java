package com.lion328.thaifixes;

public interface IThaiFixesTrueTypeGlyph {
    void setThaiFixesFlag(boolean flag);
    boolean getThaiFixesFlag();
    void setCharacter(char c);
    char getCharacter();
    void setBfOversample(float value);
    float getBfOversample();
}
