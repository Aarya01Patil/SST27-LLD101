package com.example.report;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

final class ReportBundleFacade {
    private final JsonWriter writer;
    private final Zipper zipper;
    private final AuditLog audit;

    ReportBundleFacade(JsonWriter writer, Zipper zipper, AuditLog audit) {
        this.writer = Objects.requireNonNull(writer);
        this.zipper = Objects.requireNonNull(zipper);
        this.audit = Objects.requireNonNull(audit);
    }

    Path export(Map<String,Object> data, Path outDir, String baseName) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(outDir);
        Objects.requireNonNull(baseName);
        Path json = writer.write(data, outDir, baseName);
        Path zip = zipper.zip(json, outDir.resolve(baseName + ".zip"));
        audit.log("exported " + zip);
        return zip;
    }
}


