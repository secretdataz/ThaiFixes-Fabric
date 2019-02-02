package com.lion328.thaifixes;

public interface IThaiFixesUnicodeGlyph {
    boolean shouldRemoveAdvance();
    void setRemoveAdvance(boolean flag);
    float getRealAdvance();
    void setThaiFixesFlag(boolean flag);
    boolean getThaiFixesFlag();
    void setThaiFixesCharacter(char c);
    char getThaiFixesCharacter();
}