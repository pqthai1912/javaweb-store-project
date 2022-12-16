package com.springboot.store.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.store.models.User;
import com.springboot.store.repositories.UserRepository;
import com.springboot.store.services.UserService;
//import com.springboot.store.models.User;
//@WebServlet({"/product/UserServlet","/product/UserServlet/create","/product/UserServlet/update","/product/UserServlet/edit","/product/UserServlet/delete","/product/UserServlet/reset"})

@Controller
public class UserAdminController {
	@Autowired
	UserService userService;
		
		@GetMapping("/product/register-user")
		public String UserServlet(ModelMap model) {
			User u =new User();
			//u.setUsername("PhanPhong");
			model.addAttribute("USER",u);
			model.addAttribute("ACTION","/product/SaveOrUpdate");
			return "register-user";
		}
		@PostMapping("/product/SaveOrUpdate")
		public String SaveOrUpdate(ModelMap model, @ModelAttribute("USER")User user) {
			User dao =new User();
			dao.save(user);
			System.out.println("size:" +dao.getAll().size());
			return "register-user";
		}
		
		@GetMapping("list")//Gọi list thì e truyền user lên làm gì nhỉ?
		public String list(ModelMap model) {		
			//Cần anh check thằng này nhỉ
	//	anh giup em vs a
			
//			System.out.print(userService.findAll()); // Tự sửa cho quen. Anh còn nhiều việc quá. Log chỗ này có data là được
			
			model.addAttribute("USERS",userService.findAll());//này là sẽ chạy được thôi
			
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
		
			
		/*
			User dao =new User();
			dao.delete(username);
			
			model.addAttribute("USERS",dao.getAll());

			return "user";
			*/
		
	/*	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException  {

		     User user = new User();
		     request.setAttribute("user",user);
		     request.getRequestDispatcher("/user.html").forward(request,response);
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException  {
			try {
				
			}catch (Exception e){
				e.getStackTrace();
				request.setAttribute("error","error:"+e.getMessage());
			}
			request.getRequestDispatcher("/user.html").forward(request,response);
		}
		*/
		
}
