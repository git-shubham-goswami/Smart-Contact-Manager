package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailService;

@SpringBootTest
class SmartcontactmanagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest(){

		service.sendEmail("flashxae@gmail.com",
						  "Just testing eamil service",
						  "this is scm project working on email service");
	}

}
