package com.springSecurity.JwtSpringsecurity.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springSecurity.JwtSpringsecurity.Dto.JwtAuthenticationResponse;
import com.springSecurity.JwtSpringsecurity.Dto.RefreshTokenRequest;
import com.springSecurity.JwtSpringsecurity.Dto.SignUpRequest;
import com.springSecurity.JwtSpringsecurity.Entities.User;
import com.springSecurity.JwtSpringsecurity.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignUpRequest signinRequest) {
		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}
}
