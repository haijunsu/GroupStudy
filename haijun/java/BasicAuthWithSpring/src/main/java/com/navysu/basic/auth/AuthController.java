package com.navysu.basic.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AuthController {
	
	@RequestMapping("/home")
	public ModelAndView defaultHome() {
		return new ModelAndView("home");
	}

	/**
	 * Spring security lookup jsp at src/main/webapp by default.
	 * This function changes the login.jsp to the locaition defined by app
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
}
