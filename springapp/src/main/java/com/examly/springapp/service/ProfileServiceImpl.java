package com.examly.springapp.service;

import com.examly.springapp.model.Profile;
import com.examly.springapp.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    
    @Autowired
    private ProfileRepo profileRepo;
    
    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepo.save(profile);
    }
    
    @Override
    public List<Profile> getAllProfiles() {
        return profileRepo.findAll();
    }
    
    @Override
    public Optional<Profile> getProfileById(Long id) {
        return profileRepo.findById(id);
    }
    
    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepo.save(profile);
    }
    
    @Override
    public void deleteProfile(Long id) {
        profileRepo.deleteById(id);
    }
}
