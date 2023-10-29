package com.springSecurity.JwtSpringsecurity.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springSecurity.JwtSpringsecurity.Entities.Role;
import com.springSecurity.JwtSpringsecurity.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
    User findByRole(Role role);
}
