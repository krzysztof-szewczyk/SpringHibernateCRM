package com.ozii.klinika.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

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

}
