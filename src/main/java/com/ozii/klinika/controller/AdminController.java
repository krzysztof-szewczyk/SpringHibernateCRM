package com.ozii.klinika.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ozii.klinika.user.Customer;


/**
 * Admin can everything
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String showAdmin() {
		return "admin-med-exam"; // /WEB-INF/view/admin.jsp
	}
	
	@Autowired
	private UserDetailsManager userDetailsManager;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("customer", new Customer());
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult, Model theModel) {

		String userName = theCustomer.getUserName();

		logger.info("Processing registration form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()) {
			if (theCustomer.getAuthorities() != null) {
				for (String string : theCustomer.getAuthorities()) {
					System.out.println(string);
				}
			} else {
				System.out.println("jednak null");
			}
			theModel.addAttribute("customer", new Customer());
			theModel.addAttribute("registrationError", "User name/password can not be empty.");

			logger.warning("User name/password cannot be empty.");

			return "registration-form";
		}

		// check the database if user already exists
		boolean userExists = doesUserExist(userName);

		if (userExists) {
			theModel.addAttribute("customer", new Customer());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");

			return "registration-form";
		}

		//
		// whew ... we passed all of the validation checks!
		// let's get down to business!!!
		//

		// encrypt the password
		String encodedPassword = passwordEncoder.encode(theCustomer.getPassword());

		// prepend the encoding algorithm id
		encodedPassword = "{bcrypt}" + encodedPassword;

		// give user roles
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(theCustomer.getAuthorities());
		// add default ROLE_PATIENT
		authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
		
		// create user object (from Spring Security framework)
		User tempUser = new User(userName, encodedPassword, authorities);

		// save user in the database
		userDetailsManager.createUser(tempUser);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}

	private boolean doesUserExist(String userName) {

		logger.info("Checking if user exists: " + userName);

		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);

		logger.info("User: " + userName + ", exists: " + exists);

		return exists;
	}
}