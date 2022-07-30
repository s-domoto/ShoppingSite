package com.shoppingsite.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.identifyUser(email);
		// DBにユーザが登録されていない場合
		if (userDetails == null) {
			throw new UsernameNotFoundException("登録されていないユーザです。");
		}
		return userDetails;
	}
}
