package org.launchcode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.models.Evaluator;
import org.launchcode.models.dao.EvaluatorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EvaluatorLoginController extends AbstractUserController implements Login{
	
	@Autowired
	private EvaluatorDao evaluatorDao;

	@Override
	@RequestMapping(value = "login/evaluator", method = RequestMethod.GET)
	public String loginForm() {
		// pulls up login form
		return "login/evaluator";
	}

	@Override
	@RequestMapping(value = "login/evaluator", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		// implements login
		
		//get paramaters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate parameters in order to login
		Evaluator evaluator = evaluatorDao.findByUsername(username);
		
		//evaluator username and password field were are not blank
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("login_error", "Missing username or password, please try again");
			return "login/evaluator";
		}
		
		//validate evaluator is in the database
		if(evaluator == null){
			model.addAttribute("login_error", "Username not found, please try again");
			return "login/evaluator";
		}
		
		//check password matches the to evaluator
		if(!evaluator.isMatchingPassword(password)){//this will be fixed once update Evaluator object
			model.addAttribute("login_error", "Incorrect password, please try again");
			return"login/evaluator";
		}
		
		//login by setting user in session
		setEvaluatorInSession(request.getSession(), evaluator);
		return "redirect:evaluator/home";
	}

	@Override
	//@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
