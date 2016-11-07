package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RefEvalControler {
	
	//each method should handle a url
	//create urls for home, register, login in, ref evalutions, evaluator pages
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody //use until template is developed
	public String home(){
		return "<h1>Welcome to the Hockey Referee Evaluation Site</h1>";
		//eventually this will be a reference to html template
	}

}
