package com.navysu.java.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaBasicApplication implements CommandLineRunner {
	
	@Autowired
	private MyBootStarter starter;

	public static void main(String[] args) {
		SpringApplication.run(JavaBasicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		for (String arg: args) {
			System.out.println(arg);
		}
		starter.sayHello();
	}

}
