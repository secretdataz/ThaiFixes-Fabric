package com.lion328.thaifixes;

import net.minecraft.util.Identifier;

public interface IGlyphRenderer {
    Identifier getIdentifier();
    float UMin();
    float UMax();
    float VMin();
    float VMax();
    float XMin();
    float XMax();
    float YMin();
    float YMax();
}
