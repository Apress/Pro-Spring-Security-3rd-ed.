package com.apress.JWT_Security_Authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apress.JWT_Security_Authentication.controllers.IUserService;
import com.apress.JWT_Security_Authentication.models.Role;
import com.apress.JWT_Security_Authentication.models.RoleName;
import com.apress.JWT_Security_Authentication.repository.RoleRepository;
import com.apress.JWT_Security_Authentication.repository.UserRepository;


@SpringBootApplication
public class JwtSecurityAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityAuthenticationApplication.class, args);
	}


	@Bean
	CommandLineRunner run (IUserService iUserService , RoleRepository roleRepository , UserRepository userRepository , PasswordEncoder passwordEncoder)
	{return  args ->
	{   iUserService.saveRole(new Role(RoleName.USER));
		iUserService.saveRole(new Role(RoleName.ADMIN));

	};}

}
