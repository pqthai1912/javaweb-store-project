package com.springboot.store.controllers;

import java.util.Properties;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.models.User;
import com.springboot.store.services.UserService;

@Controller
public class SendMailController {
	
	@Autowired
	JavaMailSender javaMailSender; 
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/sendEmail/{id}")
	public String ShowForm(@PathVariable("id") Long id, Model model) {
		
		User user = userService.findById(id);
		
		model.addAttribute("user", user);
		return "sendEmail";
	}
	
	@PostMapping("/sendEmail")
	public String sendMail(@RequestParam("to")String to, @RequestParam("subject")String subject,
			@RequestParam("content")String content, RedirectAttributes redirectAttributes, Model model) {
		try {
			boolean invalidFields = false;
			if(userService.findByEmail(to) == null) {
				redirectAttributes.addFlashAttribute("invalidEmail", "Vui lòng nhập địa chỉ Email.");
				invalidFields = true;
			}
			else if(subject == null) {
				redirectAttributes.addFlashAttribute("emptySubject", "Chủ đề mail không được để trống.");
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
			
			model.addAttribute("USERS",userService.findAll());
			
			return "redirect:/list";
			
		} catch (Exception e) {
			return "sendMail";
		}
		
		
	}
}
