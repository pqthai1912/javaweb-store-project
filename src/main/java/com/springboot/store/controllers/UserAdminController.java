package com.springboot.store.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.store.models.User;
import com.springboot.store.services.UserService;

@Controller
public class UserAdminController {
	@Autowired
	UserService userService;
		
		
		@PostMapping("/product/SaveOrUpdate")
		public String SaveOrUpdate(ModelMap model, @ModelAttribute("USER")User user) {
			User dao =new User();
			dao.save(user);
			System.out.println("size:" +dao.getAll().size());
			return "register-user";
		}
		
		@GetMapping("list")
		public String list(ModelMap model) {		
		
			model.addAttribute("USERS",userService.findAll());
			
			return "user";
		}
		@RequestMapping("/edit/{username}")
		public String edit(ModelMap model, @PathVariable(name="username")String username) {
			User dao =new User();
			User u = dao.findByUsername(username);
			model.addAttribute("USER",u);
			model.addAttribute("ACTION","/product/SaveOrUpdate");
			return "register-user";
			
		}
		
		@GetMapping("/delete/{id}")
		public String deleteUserById(@PathVariable("id") long id, Model model) {
			//model.addAttribute("USERS",userService.findAll());
			userService.deleteUserById(id);
			return "redirect:/list";
		}
		
		@GetMapping("/user/user-detail/")
		public String productDetail(@PathParam("id") Long id, Model model) {
			User user = userService.findById(id);
			model.addAttribute("user", user);
			return "userDetailAdmin";
		}
			
		
		
}
