package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RefEvalControler extends AbstractUserController {
	
	//each method should handle a url
	//create urls for home, register, login in, ref evalutions, evaluator pages
	
	@RequestMapping(value = "/")
	public String index(Model model){
		return "index";
		
	}

}
