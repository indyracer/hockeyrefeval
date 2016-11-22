package org.launchcode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Login {
	
	public String loginForm();
	public String login(HttpServletRequest request, Model model);
	public String logout(HttpServletRequest request);

}
