package com.navysu.basic.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/myerror")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    System.out.println("Error status:" + status);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
				System.out.println("404 Error status:" + status);
	            return "errors/error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "errors/error-500";
	        }
	    }
	    return "errors/error";
	}
}
