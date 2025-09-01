package com.example.profiles;

import java.util.Objects;

/**
 * Assembles profiles with scattered, inconsistent validation.
 */
public class ProfileService {

    // Build immutable minimal profile using centralized validation
    public UserProfile createMinimal(String id, String email) {
        return UserProfile.builder(id, email).build();
    }

    // Return a new instance with updated display name (no mutation of input)
    public UserProfile withDisplayName(UserProfile profile, String displayName) {
        Objects.requireNonNull(profile, "profile");
        if (displayName != null && displayName.length() > 100) {
            displayName = displayName.substring(0, 100);
        }
        return UserProfile.builderFrom(profile).displayName(displayName).build();
    }
}
