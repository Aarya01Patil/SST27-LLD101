package com.example.render;

import java.util.*;

final class TextStyleFactory {
    private static final Map<String, TextStyle> cache = new HashMap<>();
    static TextStyle get(String font, int size, boolean bold) {
        String key = font + "|" + size + "|" + (bold ? "B" : "N");
        return cache.computeIfAbsent(key, k -> new TextStyle(font, size, bold));
    }
}


