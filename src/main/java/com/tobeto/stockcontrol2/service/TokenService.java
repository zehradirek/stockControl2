package com.tobeto.stockcontrol2.service;

import com.tobeto.stockcontrol2.entity.Employee;
import com.tobeto.stockcontrol2.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenService {
    @Value("${application.security.jwt.SECRET_KEY}")
    private String KEY;

    public String createToken(Employee employee) {
        JwtBuilder builder = Jwts.builder();
        List<Role> employeeRoles = employee.getRole();

        String[] roles = new String[employeeRoles.size()];
        for (int i = 0; i < employeeRoles.size(); i++) {
            roles[i] = employeeRoles.get(i).getRole();
        }

        // add custom keys
        Map<String, Object> customKeys = new HashMap<>();
        customKeys.put("roles", roles);
        customKeys.put("employeeId", employee.getId().toString());
        builder = builder.claims(customKeys);
        Instant tarih = Instant.now().plus(15, ChronoUnit.MINUTES);

        builder = builder.subject("login").id(employee.getEmail()).issuedAt(new Date()).expiration(Date.from(tarih));

        return builder.signWith(getKey()).compact();
    }

    public Claims tokenKontrol(String token) {
        JwtParser builder = Jwts.parser().verifyWith(getKey()).build();
        return builder.parseSignedClaims(token).getPayload();
    }

    private SecretKey getKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
        return key;
    }
}