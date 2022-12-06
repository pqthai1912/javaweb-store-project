package com.springboot.store.controllers;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.services.UserService;

@Controller
public class SendMailController {
	
	@Autowired
	JavaMailSender javaMailSender; 
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/sendEmail")
	public String ShowForm(Model model) {
		model.addAttribute("invalidEmail", model.asMap().get("invalidEmail"));
		model.addAttribute("emptySubject", model.asMap().get("emptySubject"));
		return "sendEmail";
	}
	
	@PostMapping("/sendEmail")
	public String sendMail(@RequestParam("to")String to, @RequestParam("subject")String subject,
			@RequestParam("content")String content, RedirectAttributes redirectAttributes) {
		try {
			boolean invalidFields = false;
			if(userService.findByEmail(to) == null) {
				redirectAttributes.addFlashAttribute("invalidEmail", true);
				invalidFields = true;
			}
			else if(subject == null) {
				redirectAttributes.addFlashAttribute("emptySubject", true);
				invalidFields = true;
			}
			else if(invalidFields) {
				return "redirect:/sendEmail";
			}
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("smtp.gmail.com");
		    mailSender.setPort(587);
		    
		    mailSender.setUsername("phonglechanh116@gmail.com");
		    mailSender.setPassword("xoswwauoryshfqfb");
		    
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
//		    props.put("mail.debug", "true");
		    
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(to);
			msg.setSubject(subject);
			msg.setText(content);
			mailSender.send(msg);

			return "redirect:/sendEmail";
			
		} catch (Exception e) {
			return "sendMail";
		}
		
		
	}
}
