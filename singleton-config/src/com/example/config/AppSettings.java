package com.example.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread-safe, lazy-initialized Singleton for application settings.
 * Guards against multiple instances via reflection and preserves singleton on serialization.
 */
public class AppSettings implements Serializable {
    private static final long serialVersionUID = 1L;

    // Tracks whether the constructor has successfully created the singleton once
    private static final AtomicBoolean constructed = new AtomicBoolean(false);

    private final Properties props = new Properties();

    private AppSettings() {
        // Prevent creating a second instance via reflection
        if (!constructed.compareAndSet(false, true)) {
            throw new IllegalStateException("AppSettings singleton already constructed");
        }
    }

    // Initialization-on-demand holder idiom for lazy, thread-safe init
    private static class Holder {
        private static final AppSettings INSTANCE = new AppSettings();
    }

    public static AppSettings getInstance() {
        return Holder.INSTANCE;
    }

    public void loadFromFile(Path file) {
        try (InputStream in = Files.newInputStream(file)) {
            props.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }

    // Ensure deserialization returns the one true instance
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
