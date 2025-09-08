package com.example.video;

import java.nio.file.Path;
import java.util.Objects;

final class VideoPipelineFacade {
    private final Decoder decoder;
    private final FilterEngine filters;
    private final SharpenAdapter sharpen;
    private final Encoder encoder;

    VideoPipelineFacade(Decoder decoder, FilterEngine filters, SharpenAdapter sharpen, Encoder encoder) {
        this.decoder = Objects.requireNonNull(decoder);
        this.filters = Objects.requireNonNull(filters);
        this.sharpen = Objects.requireNonNull(sharpen);
        this.encoder = Objects.requireNonNull(encoder);
    }

    Path process(Path src, Path out, boolean gray, Double scale, Integer sharpenStrength) {
        Frame[] frames = decoder.decode(src);
        if (gray) frames = filters.grayscale(frames);
        if (scale != null) frames = filters.scale(frames, scale);
        if (sharpenStrength != null) frames = sharpen.apply(frames, sharpenStrength);
        return encoder.encode(frames, out);
    }
}


