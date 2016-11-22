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
public class OfficialSignUpController extends AbstractUserController{

	@Autowired
	private OfficialDao officialDao;

	@RequestMapping(value = "/official/signup", method = RequestMethod.GET)
	public String signupForm(){
		return "signup";
	}

	@RequestMapping(value = "/official/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model){
		
		//parameters to get from signup form
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String level = request.getParameter("level"); //getParameter always comes in as a String, need to convert to int below
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		int intLevel = Integer.parseInt(level); //to convert level from string to int
		//validate parameters
		//username is valid
		if(!Official.isValidUsername(username)) {
			model.addAttribute("username_error", "Invalid Username, please try again");
			return "/official/signup";
		}
		
		//password is valid
		if(!Official.isValidPassword(password)) {
			model.addAttribute("password_error", "Invalid password.  Passwords must be between 6 to 20 characters.  Please try again");
			return "/official/signup";
		}
		
		//password matches verify
		if(!password.equals(verify)) {
			model.addAttribute("verify_error", "Passwords do not match, please try again");
			return "/official/signup";
		}
		
		//if all validates, create new official and put them into session
		
		Official newOfficial = new Official(firstName, lastName, username, password, intLevel);
		officialDao.save(newOfficial); //saves to db
		setOfficialInSession(request.getSession(), newOfficial);
		
		return "redirect:official/evaluations";
	}
	
	

}
