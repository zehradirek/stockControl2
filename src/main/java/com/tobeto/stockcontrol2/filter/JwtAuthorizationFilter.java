package com.tobeto.stockcontrol2.filter;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.tobeto.stockcontrol2.service.TokenService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Autherization header'ı gönderilmiş ise ve "Bearer " ile başlıyorsa
                String token = authHeader.substring(7);
                if (!"".equals(token)) {
                    // token gönderilmiş ise
                    Claims claims = tokenService.tokenKontrol(token);
                    String email = claims.getId();
                    @SuppressWarnings("unchecked")
                    ArrayList<String> role = claims.get("role", ArrayList.class);
                    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                    role.forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r)));

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, "", authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            // jwt token kontrolü sırasında hata oluştuysa bir şey yapma
        }
        filterChain.doFilter(request, response);
    }

}


