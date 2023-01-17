package com.example.controller;

import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto){
        LoginResponseDTO result = authService.login(dto);
        return ResponseEntity.ok(result);
    }
}
