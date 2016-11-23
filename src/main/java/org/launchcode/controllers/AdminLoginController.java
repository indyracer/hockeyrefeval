package org.launchcode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.models.Admin;
import org.launchcode.models.dao.AdminDoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminLoginController extends AbstractUserController implements Login{
	
	@Autowired
	private AdminDoa adminDao;

	@Override
	@RequestMapping(value = "login/admin", method = RequestMethod.GET)
	public String loginForm() {
		//pulls up login form
		return "login/admin";
	}

	@Override
	@RequestMapping(value = "login/admin", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		// implements login
		
		//get parameters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate parameters in order to login
		Admin admin = adminDao.findByUsername(username);
		
		//validate login fields were filled in
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("login_error", "Missing username or password, please try again");
			return "login/admin";
		}
		
		//validate admin is in the database
		if(admin == null){
			model.addAttribute("login_error", "Username not found, please try again");
			return "login/admin";
		}
		
		//check that password matches to admin
		if(!admin.isMatchingPassword(password)){
			model.addAttribute("login_error", "Incorrect password, please try again");
			return "login/admin";
		}
		
		//log them in by setting admin in session
		setAdminInSession(request.getSession(), admin);
		return "redirect:admin/home";
		
	}

	@Override
	//@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin/login";
	}

}
