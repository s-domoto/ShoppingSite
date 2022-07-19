package com.shoppingsite.controller;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index(Model model) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("password1"));
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

	@PostMapping("/login")
	public ModelAndView displayIndexPage(ModelAndView mav) {
		
		return mav;
	}
}
