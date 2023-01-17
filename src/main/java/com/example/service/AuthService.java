package com.example.service;

import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.entity.profile.ProfileEntity;
import com.example.exception.LoginOrPasswordIncorrectException;
import com.example.repositiry.ProfileRepository;
import com.example.util.JwtTokenUtil;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    public LoginResponseDTO login(LoginDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndPassword(dto.getUsername(), MD5Util.MD5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new LoginOrPasswordIncorrectException("Username or Password incorrect !!!");
        }
        ProfileEntity entity = optional.get();

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setToken(JwtTokenUtil.encode(entity.getUsername(),entity.getRole()));
        return responseDTO;
    }
}
