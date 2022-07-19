package com.shoppingsite.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login").usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error")
				.permitAll()
				).logout(logout -> logout
						.logoutSuccessUrl("/")
						).authorizeHttpRequests(ahr -> ahr
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								.mvcMatchers("/").permitAll()
								.anyRequest().authenticated()
								);
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}