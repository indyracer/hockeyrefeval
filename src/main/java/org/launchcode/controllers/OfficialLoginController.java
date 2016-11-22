package org.launchcode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.models.Official;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import or.launchcode.models.dao.OfficialDao;

@Controller
public class OfficialLoginController extends AbstractUserController implements Login {
	
	@Autowired
	private OfficialDao officialDao;

	@Override
	@RequestMapping(value="login/official", method = RequestMethod.GET)
	public String loginForm() {
		// pulls up login form
		return "official/login";
	}

	@Override
	@RequestMapping(value="login/official", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		// implements the login
		
		//get parameters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate parameters in order to login
		Official official = officialDao.findByUsername(username);
		
		//official username and password were submitted
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("login_error", "Missing username or password, please try again");
			return "login/official";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("login_error", "Username not found, please try again");
			return "login/official";
		}
		
		//check password matches to official
		if(!official.isMatchingPassword(password)){
			model.addAttribute("login_error", "Incorrect password, please try again");
			return "login/official";
		}
		
		//log them in by setting official in session
		setOfficialInSession(request.getSession(), official);
		return "redirect:official/home";
	}

	@Override
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
