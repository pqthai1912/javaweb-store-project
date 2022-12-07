package com.springboot.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springboot.store.handler.LoginSuccessHandler;
import com.springboot.store.services.impl.UserSecurityService;

import util.SecurityUtility;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}
	
	// những đường dẫn không cần login
	private static final String[] PUBLIC_MATCHERS = {
		
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/new-user",
			"/login",
			"/store",
			"/sendEmail",
			"/result",
			"/sendnail",
			"/login2",
			"/product-detail",
			
			
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers("/product/**").hasRole("ADMIN")
			.antMatchers("/dashboard/**").hasRole("ADMIN") // ngan k cho client vao
			.anyRequest().authenticated();
		
		http
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error")
			.loginPage("/login")
			.successHandler(loginSuccessHandler) // them dong nay xu ly redirect cho login thanh cong
			.permitAll()
//			.and().csrf().disable().cors().disable() 
//			.formLogin().failureUrl("/result?error")
//			.loginPage("/result").permitAll()
//			.and().csrf().disable().cors().disable()
//			.formLogin().failureUrl("/login2?error")
//			.loginPage("/login2").permitAll()
//			.and().csrf().disable().cors().disable()
//			.formLogin().failureUrl("/sendEmail?error")
//			.loginPage("/sendEmail").permitAll()
//			.and().csrf().disable().cors().disable()
//			.formLogin().failureUrl("/sendmail?error")
//			.loginPage("/sendnail").permitAll() // cái này gây lỗi k check login dc
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe().key("aSecretKey");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired private LoginSuccessHandler loginSuccessHandler;
}

