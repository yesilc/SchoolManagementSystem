package com.SchoolManagementSystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
We want this filter to be active every time we get a request so every time the user sends a
request we want our filter to get fired and do all the job that we want that we wanted to do*/
@Component
@RequiredArgsConstructor //Creates a constructor that takes final and NonNull variables as parameters in the class.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        //Check JWT token
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response); // to do next filter
            return;
        }
        //extract the token from this header
        //beginIndex 7 ->"Bearer_"
        jwt = authHeader.substring(7);
        //UserDetailsService
        //the user already within our database or not
        //for this user email from the token i need a class that can manipulate this JWT token
        userEmail = jwtService.extractUsername(jwt);
        /*if we have our user email and the user is not authenticated we get the user details
        from the database and check the user is valid or not, if the user and the token is valid so we
        create an object of type usernamepass... and then we extend or reinforce this authentication token
        with the details of our request and then we update authentication token*/
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //once the user is not connected
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            //if token is valid, we need to update the SecurityContextHolder and send the request to our dispatcher servlet
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
