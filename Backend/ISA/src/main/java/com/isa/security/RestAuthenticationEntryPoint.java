package com.isa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.isa.security.exception.TokenNotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;
	
	//Metoda koja se izvrsava ukoliko neautorizovani korisnik pokusa da pristupi zasticenom REST servisu
	//Metoda vraca 401 Unauthorized response, ukoliko postoji Login Page u aplikaciji, pozeljno je da se korisnik redirektuje na tu stranicu
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //logger.info("Redirecting unauthenticated request [" + request.getMethod() + ", " + request.getRequestURI() + "] to login page.");
    	//response.sendRedirect("/login.html");
    	//response.sendError(HttpStatus.UNAUTHORIZED.value());
    	//throw new TokenNotValidException("Token not valid");
    	resolver.resolveException(request, response, null, new TokenNotValidException("Token not valid"));
    }
}