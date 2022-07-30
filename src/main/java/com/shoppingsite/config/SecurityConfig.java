package com.shoppingsite.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.shoppingsite.auth.CustomAuthenticationFailureHandler;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login").usernameParameter("email").passwordParameter("password")
				// 認証失敗時はメールアドレスの入力値を保持するためにカスタムのハンドラを使う
				.failureHandler(customAuthenticationFailureHandler())
				.defaultSuccessUrl("/")
//				.failureUrl("/login?error")
//				.failureHandler((req, res, exp) -> {
//					res.sendRedirect("/login?error=true&email=" + req.getParameter("email"));
//				})
				.permitAll()
				).logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						).authorizeHttpRequests(ahr -> ahr
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								.mvcMatchers("/").permitAll()
								.anyRequest().authenticated()
								);
		return http.build();

	}

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}