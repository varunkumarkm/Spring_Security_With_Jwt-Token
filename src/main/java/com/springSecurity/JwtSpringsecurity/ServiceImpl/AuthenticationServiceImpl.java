package com.springSecurity.JwtSpringsecurity.ServiceImpl;

import java.util.HashMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springSecurity.JwtSpringsecurity.Dto.JwtAuthenticationResponse;
import com.springSecurity.JwtSpringsecurity.Dto.RefreshTokenRequest;
import com.springSecurity.JwtSpringsecurity.Dto.SignUpRequest;
import com.springSecurity.JwtSpringsecurity.Entities.Role;
import com.springSecurity.JwtSpringsecurity.Entities.User;
import com.springSecurity.JwtSpringsecurity.Repositories.UserRepository;
import com.springSecurity.JwtSpringsecurity.Service.AuthenticationService;
import com.springSecurity.JwtSpringsecurity.Service.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtService jwtService;
	
	public User signUp(SignUpRequest signUpRequest) {
		
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
		
	}
	public JwtAuthenticationResponse signin(SignUpRequest signinRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				signinRequest.getEmail(), 
				signinRequest.getPassword())
				);
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> 
		         new IllegalArgumentException("Invalid email or password."));
		
		var jwt = jwtService.generateToken(user);
		
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		
		return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			
			return jwtAuthenticationResponse;
		}
		return null;
	}
}
















