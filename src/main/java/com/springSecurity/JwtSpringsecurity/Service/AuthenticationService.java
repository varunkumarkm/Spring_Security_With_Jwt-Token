package com.springSecurity.JwtSpringsecurity.Service;

import com.springSecurity.JwtSpringsecurity.Dto.JwtAuthenticationResponse;
import com.springSecurity.JwtSpringsecurity.Dto.RefreshTokenRequest;
import com.springSecurity.JwtSpringsecurity.Dto.SignUpRequest;
import com.springSecurity.JwtSpringsecurity.Entities.User;

public interface AuthenticationService {

	User signUp(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse signin(SignUpRequest signinRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
