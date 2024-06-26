package com.HotelMalik.reserv2.filter;

import com.HotelMalik.reserv2.Security.JwtService;
import com.HotelMalik.reserv2.Security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsImpl userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsImpl userDetails, UserDetailsImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader= request.getHeader("Authorization");
       if(authHeader ==null || !authHeader.startsWith("Bearer")){
           filterChain.doFilter(request,response);
           return;
       }
       String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails  = userDetailsService.loadUserByUsername(username);
            if (jwtService.isValid(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

    filterChain.doFilter(request,response);
    }
}
