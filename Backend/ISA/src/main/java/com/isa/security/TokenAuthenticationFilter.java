package com.isa.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.isa.security.exception.TokenNotValidException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Filter koji ce presretati svaki zahtev klijenta ka serveru
//Sem nad putanjama navedenim u WebSecurityConfig.configure(WebSecurity web)
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String email;
        String authToken = tokenHelper.getToken(request);

        if (authToken != null) {
            //uzmi username iz tokena
        	email = tokenHelper.getEmailFromToken(authToken);
            if (email != null) {
                // uzmi user-a na osnovu username-a
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if(userDetails == null) {
                	throw new TokenNotValidException("Token not valid");
                }
                // logger.info("User " + username + " is accessing with token");
                //proveri da li je prosledjeni token validan
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    // kreiraj autentifikaciju
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(authToken, userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // logger.info("User " + username + " accessed with valid token");
                }
                else {
                	// logger.info("User " + username + " tried to access with invalid token");
                }
            }
            else{
            	// logger.info("AuthToken has no username");
            }
        }
        else {
        	// logger.info("Request without AuthToken");
        }
        chain.doFilter(request, response);
    }
}