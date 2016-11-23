package org.launchcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.launchcode.controllers.AbstractUserController;
import org.launchcode.models.Official;
import org.launchcode.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.AbstractController;

//QUESTION:  DO I NEED TO SET UP AN AUTHENTICATIONINTERCEPTOR FOR EACH TYPE OF USER OR CAN THEY ALL BE IN ONE CLASS?
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	OfficialDao officialDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		//list of restricted URLs
		List<String> authPages = Arrays.asList("/official/evaluations", "/official/evalrequest"); //is there a way to say all pages beyond the '/official' url?
		
		//require sign-in for the authorized pages
		if(authPages.contains(request.getRequestURI())) {
			boolean isLoggedIn = false;
			Official official;
			Integer officialId = (Integer) request.getSession().getAttribute(AbstractUserController.officialSessionKey); //this will be fixed when controllers are completed
			
			if(officialId != null){
				official = officialDao.findByUid(officialId);
				
				if(officialId != null){
					isLoggedIn = true;
				}
				
			}
			
			//if user not logged in, redirect to login page
			if(!isLoggedIn){
				response.sendRedirect("/official/login");
				return false;
			}
		}
		
		return true;
		
	}

}
