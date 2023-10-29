package com.springSecurity.JwtSpringsecurity.ServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springSecurity.JwtSpringsecurity.Repositories.UserRepository;
import com.springSecurity.JwtSpringsecurity.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetailsService userDetailsService() {
	return new UserDetailsService() {
		
		@Override
		public UserDetails loadUserByUsername(String username) {
			return userRepository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		}
	  };
	}
}
