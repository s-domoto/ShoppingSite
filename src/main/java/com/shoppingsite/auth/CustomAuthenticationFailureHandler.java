package com.shoppingsite.auth;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationFailureHandler
implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception)
					throws IOException, ServletException {
		String fowardUrl = "/login?error";

		RequestDispatcher dispatch = request.getRequestDispatcher(fowardUrl);
		// フォワードを使うことでメールアドレスの入力値を保持
		dispatch.forward(request, response);
		
//		String email = request.getParameter("email");
//		String redirectURL = "/login/error?email=" + email;
//		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//		response.setHeader("Location", redirectURL);
//		response.setStatus(response.SC_MOVED_PERMANENTLY);
//		response.sendRedirect(request.getContextPath() + redirectURL);		
	}
}
