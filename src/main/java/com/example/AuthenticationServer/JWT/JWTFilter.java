package com.example.AuthenticationServer.JWT;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path =  request.getRequestURI();
//        System.out.println("\nPath: "+(path));
        if(path.equals("/api/signup") || path.equals("/api/login") || path.equals("/api/sendOTP")){
//            System.out.println(request.get);
            filterChain.doFilter(request,response);
            return;
        }
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
             String token = header.substring(7);
             if(jwtUtility.validateToken(token) && !jwtUtility.isTokenExpired(token)){
                 String userName = jwtUtility.extractUserEmail(token);
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,null,null);
//                 System.out.println(token);
//               Now store this token as context in securityContextHolder....
                 if(SecurityContextHolder.getContext().getAuthentication() == null){
                     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                 }
//                 System.out.println(SecurityContextHolder.getContext().getAuthentication());
             }
        }
        filterChain.doFilter(request,response);
    }
}
