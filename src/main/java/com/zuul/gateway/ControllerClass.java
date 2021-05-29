package com.zuul.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/route1")
	public String getDetails() {
		
		return "This is details from route1 of ZuulAPI1 "+ env.getProperty("server.port");
	}
	
	@RequestMapping("/route4")
	public String getDetailsfrmAnother() {
		
		return "This is details from route4 of ZuulAPI1 "+ env.getProperty("server.port");
	}

}
