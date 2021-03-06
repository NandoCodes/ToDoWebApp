package com.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
private UserDetailsService userDetailsSerivice;

@Bean
public BCryptPasswordEncoder getPasswordEncoder()
{
	return new BCryptPasswordEncoder();
}
	
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception
{
	auth.userDetailsService(userDetailsSerivice)
	.passwordEncoder(getPasswordEncoder());
}
@Override
protected void configure(HttpSecurity http) throws Exception
{
	http
	.authorizeRequests()
	.antMatchers("/register").permitAll()
	.antMatchers("/").permitAll()
	.anyRequest().hasRole("USER")
	.and().formLogin().loginPage("/").permitAll()
	.defaultSuccessUrl("/dash")
	.and().logout() .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
}
}