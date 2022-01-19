package com.navysu.java.basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navysu.java.basic.service.HelloService;

@Component
public class MyBootStarter {
	
	@Autowired
	private HelloService helloService;
	
	public void sayHello() {
		helloService.hello();
	}

}
