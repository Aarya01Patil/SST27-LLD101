package com.example.imports;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

final class CsvProfileImporter implements ProfileImporter {
    private final NaiveCsvReader reader;
    private final ProfileService service;

    CsvProfileImporter(NaiveCsvReader reader, ProfileService service) {
        this.reader = Objects.requireNonNull(reader);
        this.service = Objects.requireNonNull(service);
    }

    @Override
    public int importFrom(Path csvFile) {
        Objects.requireNonNull(csvFile);
        int imported = 0;
        List<String[]> rows = reader.read(csvFile);
        for (String[] r : rows) {
            if (r.length < 3) { System.out.println("Skip: missing columns"); continue; }
            String id = r[0] != null ? r[0].trim() : null;
            String email = r[1] != null ? r[1].trim() : null;
            String displayName = r[2] != null ? r[2].trim() : "";
            if (id == null || id.isEmpty()) { System.out.println("Skip: missing id"); continue; }
            if (email == null || email.isEmpty() || !email.contains("@")) { System.out.println("Skip: bad email"); continue; }
            service.createProfile(id, email, displayName);
            imported++;
        }
        return imported;
    }
}


