package com.ozii.klinika.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ozii.klinika.user.Customer;

/**
 * Admin can everything
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	Logger logger = Logger.getLogger(getClass().getName());
	
	// To see all users
    @Autowired
    private SessionRegistry sessionRegistry;

	@GetMapping("/")
	public String showAdmin() {
		final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		for(final Object principal : allPrincipals) {
            if(principal instanceof Customer) {
                final Customer user = (Customer) principal;

                // Do something with user
                logger.info(user.toString());
            }
        }
		return "list-employees"; 
	}

}
