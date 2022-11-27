/*
package com.springboot.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller


public class MailController {
	@Autowired
	JavaMailSender javaMailSender;
	
	
	
	//@RequestMapping("/header")
	public String showForm() {
		return "sendEmail";
		
	}
	@RequestMapping("/send")
	public String sendMail(@RequestParam("to")String to, @RequestParam("subject")String subject,
			@RequestParam("content")String content) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(content);
		javaMailSender.send(msg);
		
		
		return "result";
	}
}
*/
