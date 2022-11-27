package com.springboot.store.controllers;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.models.Address;
import com.springboot.store.models.User;
import com.springboot.store.services.UserService;
import com.springboot.store.services.impl.UserSecurityService;

import util.SecurityUtility;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
//	@Autowired
//	private OrderService orderService;

	// check đã log hay chưa
	@RequestMapping("/login")
	public String log(Model model) {
		model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
		model.addAttribute("emailExists", model.asMap().get("emailExists"));
		return "login"; // pass, register thêm dc
	}
	
	
	/*
	@RequestMapping("/login2")
	public String log2(Model model2) {
		model2.addAttribute("usernameExists", model2.asMap().get("usernameExists"));
		model2.addAttribute("emailExists", model2.asMap().get("emailExists"));
		return "login2"; // pass, register thêm dc
	}
	*/
	@Autowired
	JavaMailSender javaMailSender;
	
	@RequestMapping("/sendEmail")
	public String ShowForm() {
		return "sendEmail";
	}
	
	@RequestMapping("/sendnail")
	public String sendMail(@RequestParam("to")String to, @RequestParam("subject")String subject,
			@RequestParam("content")String content) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(content);
		javaMailSender.send(msg);
		return "login";
	}
	
	
	/*
	@RequestMapping("/sendEmail")
	public String sendEmail(Model model2) {
		model2.addAttribute("usernameExists", model2.asMap().get("usernameExists"));
		model2.addAttribute("emailExists", model2.asMap().get("emailExists"));
		return "sendEmail"; // pass, register thêm dc
	}
	
	*/
	
	@RequestMapping("/my-profile")
	public String myProfile(Model model, Authentication authentication) {				
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "myProfile";
	}

//	
	@RequestMapping("/my-address")
	public String myAddress(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "myAddress";
	}
//	
	@RequestMapping(value="/update-user-address", method=RequestMethod.POST)
	public String updateUserAddress(@ModelAttribute("address") Address address, 
			Model model, Principal principal) throws Exception {
		User currentUser = userService.findByUsername(principal.getName());
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		currentUser.setAddress(address);
		userService.save(currentUser);
		return "redirect:/my-address";
	}
	
	//	Tạo user mới khi đăng ký tài khoản
	@RequestMapping(value="/new-user", method=RequestMethod.POST)
	public String newUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResults,
							  @ModelAttribute("new-password") String password, 
							  RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());	
		boolean invalidFields = false;
		if (bindingResults.hasErrors()) {
			return "redirect:/login";
		}		
		if (userService.findByUsername(user.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("usernameExists", true);
			invalidFields = true;
		}
		if (userService.findByEmail(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("emailExists", true);
			invalidFields = true;
		}	
		if (invalidFields) {
			return "redirect:/login";
		}		
		user = userService.createUser(user.getUsername(), password,  user.getEmail(), Arrays.asList("ROLE_USER"));	
		userSecurityService.authenticateUser(user.getUsername());
		return "redirect:/my-profile";  
	}
	
//		
	@RequestMapping(value="/update-user-info", method=RequestMethod.POST)
	public String updateUserInfo( @ModelAttribute("user") User user,
								  @RequestParam("newPassword") String newPassword,
								  Model model, Principal principal) throws Exception {
		User currentUser = userService.findByUsername(principal.getName());
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		/*check username already exists*/
		User existingUser = userService.findByUsername(user.getUsername());
		if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
			model.addAttribute("usernameExists", true);
			return "myProfile";
		}	
		/*check email already exists*/
		existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
			model.addAttribute("emailExists", true);
			return "myProfile";
		}			
		/*update password*/
		if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			if(passwordEncoder.matches(user.getPassword(), dbPassword)){
				currentUser.setPassword(passwordEncoder.encode(newPassword));
			} else {
				model.addAttribute("incorrectPassword", true);
				return "myProfile";
			}
		}		
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());		
		userService.save(currentUser);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);				
		userSecurityService.authenticateUser(currentUser.getUsername());		
		return "myProfile";
	}
	
//	
//	@RequestMapping("/my-orders")
//	public String myOrders(Model model, Authentication authentication) {
//		User user = (User) authentication.getPrincipal();
//		model.addAttribute("user", user);
//		List<Order> orders = orderService.findByUser(user);
//		model.addAttribute("orders", orders);
//		return "myOrders";
//	}
	
//	
//	@RequestMapping("/order-detail")
//	public String orderDetail(@RequestParam("order") Long id, Model model) {
//		Order order = orderService.findOrderWithDetails(id);
//		model.addAttribute("order", order);
//		return "orderDetails";
//	}
	
}
