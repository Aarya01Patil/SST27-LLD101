package com.example.render;

public class Glyph {
    private final char ch;
    private final TextStyle style; // flyweight

    public Glyph(char ch, TextStyle style) {
        this.ch = ch; this.style = style;
    }

    public int drawCost() { return style.size + (style.bold ? 10 : 0); }
    public char getCh() { return ch; }
    public TextStyle getStyle() { return style; }
}
