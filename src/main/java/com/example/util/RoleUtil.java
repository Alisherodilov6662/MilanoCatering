package com.example.util;

import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.exception.ForbiddenException;

public class RoleUtil {
    public static void checkRoleToAdmin(JwtDTO jwtDTO) {
        if (!jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            throw new ForbiddenException("Method not allowed");
        }
    }
}
