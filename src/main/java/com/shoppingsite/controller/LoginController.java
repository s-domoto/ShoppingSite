package com.shoppingsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingsite.auth.User;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal User user) {
		if (user == null) {
			return "login";
		}
		model.addAttribute("name", user.getUsername());
		return "index";
	}
	@GetMapping("/login")
	public String displayLoginPage() {
		return "login";
	}
	@PostMapping("/login")
	public ModelAndView loginPost(@ModelAttribute("loginForm") @Validated User user, BindingResult result, ModelAndView mav) {
		ModelAndView res = null;
		
		return res;
	}
	
	@PostMapping(value="/login", params="error")
	public String error(Model model) {
		model.addAttribute("errorMsg", "ログイン認証に失敗しました");
		return "/login";
	}
}
