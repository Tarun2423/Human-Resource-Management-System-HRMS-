package com.task.hr;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.io.IOException;
import java.text.ParseException;

@WebFilter(
	    filterName = "JWTFilter",
	    urlPatterns = {"/secured/*"}, // Adjust the URL pattern to exclude your login page
	    servletNames = {"Login"}
	)
public class JwtFilter implements Filter {
	private String secretKey;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
        this.secretKey = new Login().getSecretKey();
    }
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;

	        String jwt = extractJwtFromCookies(httpRequest.getCookies());
	        
	        if(isValid(jwt)) {
	        	chain.doFilter(request, response);
	        }else {
	            
	           
	            response.getWriter().write("Unauthorized");
	            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }
		
	}

	private boolean isValid(String jwt) {
	    if (jwt != null && !jwt.trim().isEmpty()) {
	        try {
	            return SignedJWT.parse(jwt).verify(new MACVerifier(secretKey));
	        } catch (ParseException | JOSEException e) {
	            e.printStackTrace();
	            return false;
	        }
	    } else {
	      
	        return false;
	    }
	}


	private String extractJwtFromCookies(Cookie[] cookies) {
		   if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("jwtToken".equals(cookie.getName())) { 
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	}
	
	
}
