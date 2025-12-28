package com.examly.springapp.service;

import com.examly.springapp.model.Profile;
import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile saveProfile(Profile profile);
    List<Profile> getAllProfiles();
    Optional<Profile> getProfileById(Long id);
    Profile updateProfile(Profile profile);
    void deleteProfile(Long id);
}
