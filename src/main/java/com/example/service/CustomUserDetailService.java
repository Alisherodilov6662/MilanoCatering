package com.example.service;

import com.example.config.CustomUserDetails;
import com.example.entity.profile.ProfileEntity;
import com.example.repositiry.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByUsername(username);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException("Bad Credential");
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile);
    }
}
