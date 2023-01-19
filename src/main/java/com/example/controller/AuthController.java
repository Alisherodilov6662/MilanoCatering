package com.example.controller;

import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     *
     * @param  dto LoginDTO
     *  username,password:
     * @return LoginResponseDTO
     * name,surname,username,role,token.
     *
     */
    @PostMapping("/login")
//    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto){
        LoginResponseDTO result = authService.login(dto);
        return ResponseEntity.ok(result);
    }
}
