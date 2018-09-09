package com.ozii.klinika.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 	// reference to our security data source
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// add our users for in memory authentication
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication().withUser(users.username("pacjent").password("pacjent").roles("PATIENT"));
//		auth.inMemoryAuthentication().withUser(users.username("doktor").password("doktor").roles("PATIENT", "DOCTOR"));
//		auth.inMemoryAuthentication().withUser(users.username("moderator").password("moderator").roles("PATIENT", "MODERATOR"));
//		auth.inMemoryAuthentication().withUser(users.username("admin").password("admin").roles("PATIENT", "MODERATOR", "DOCTOR", "ADMIN"));
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").hasRole("PATIENT")
		.antMatchers("/doctor/**").hasRole("DOCTOR")
		.antMatchers("/admin/**").hasAnyRole("ADMIN", "MODERATOR")
		.and()
		.formLogin()
		.loginPage("/show-login-page")
		.loginProcessingUrl("/authenticateTheUser")
		.usernameParameter("username")
		.passwordParameter("password")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied");
		// spring will automagically read "username" and "password"
		// permitAll() - allow everyone to see login page
		// need TO DO controller for /showMyLoginPage, and dont need controller for
		// /authenticateTheUser (for free - Spring Security magic LOL)
//		super.configure(http);
	}
	
	@Bean
	public UserDetailsManager userDetailManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager;
	}

}
