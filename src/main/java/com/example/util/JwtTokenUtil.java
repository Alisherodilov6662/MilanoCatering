package com.example.util;

import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtil {
    public static final int tokenLiveTime = 1000 * 3600 * 24; // 1 - day this is exp time jwt
    private static final String secretKey = "TeamSecret"; // signing key

    public static String encode(String username, ProfileRole role){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512,secretKey);

        jwtBuilder.claim("username",username);
        jwtBuilder.claim("role",role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + tokenLiveTime));
        jwtBuilder.setIssuer("YouTube by TeamSecret");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token){
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();

        String username = (String) claims.get("username");
        String role = (String) claims.get("role");

        ProfileRole profileRole = ProfileRole.valueOf(role);
        return new JwtDTO(username,profileRole);
    }
    public static String decodeEmail(String token){
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        String username = (String) claims.get("username");
        return username;
    }

}
