package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	Logger logger = LoggerFactory.getLogger(Home.class);
	 @RequestMapping("/welcome")

	    public String welcome() {
		 logger.debug("login success done");

	        String text = "this is private page ";
	        text+= "this page is not allowed to unauthenticated users";
	        return text;
	    }
	 @RequestMapping("/test")

	    public String test() {
		 logger.debug("login error ");

		 throw new RuntimeException("Opps Exception raised....");
	        
	    }

	 @RequestMapping("/getusers")
	 
	    public String getUser() {
	        return "{\"name\":\"Sadiya\"}";
	    }
}
