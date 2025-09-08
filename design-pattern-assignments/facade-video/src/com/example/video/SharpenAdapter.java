package com.example.video;

final class SharpenAdapter {
    private final LegacySharpen legacy;
    SharpenAdapter(LegacySharpen legacy) { this.legacy = legacy; }
    Frame[] apply(Frame[] frames, int strength) {
        // simulate conversion to a handle and back
        String handle = "HANDLE:";
        String newHandle = legacy.applySharpen(handle, strength);
        return frames; // pretend it used newHandle to modify frames
    }
}


