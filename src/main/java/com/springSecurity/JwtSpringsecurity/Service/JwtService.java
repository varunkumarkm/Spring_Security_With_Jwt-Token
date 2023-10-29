package com.springSecurity.JwtSpringsecurity.Service;

import java.util.Map;	
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	
	 String extractUserName(String token);
	
	 String generateToken(UserDetails userDetails);
	
	 boolean isTokenValid(String token, UserDetails userDetails);

	 Object generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);


}
