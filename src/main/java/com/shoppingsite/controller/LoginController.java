package com.shoppingsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingsite.auth.User;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal User user) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		if (user == null) {
			return "login";
		}
		return "index";
	}
	@GetMapping("/login")
	public String displayLoginPage() {
		return "login";
	}
	@GetMapping(value="/login", params="error")
	public String error(Model model) {
		model.addAttribute("errorMsg", "ログイン認証に失敗しました");
		return "/login";
	}
}
