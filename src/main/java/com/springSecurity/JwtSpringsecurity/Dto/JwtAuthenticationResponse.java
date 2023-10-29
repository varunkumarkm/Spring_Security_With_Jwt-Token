package com.springSecurity.JwtSpringsecurity.Dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	private Object refreshToken;
}
