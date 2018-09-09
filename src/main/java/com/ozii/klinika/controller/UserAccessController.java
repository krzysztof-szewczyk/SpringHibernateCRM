package com.ozii.klinika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccessController {
	@GetMapping("/")
	public String showHome() {
		return "home"; // /WEB-INF/view/home.jsp
	}
		
	/**
	 * Patient can see only his own medical examination
	 * @return
	 */
	@GetMapping("/patient")
	public String showPatient() {
		
		return "patient-med-exam"; 
	}
	
//	/**
//	 * Doctor can create and save patient's medical examination.
//	 * @return
//	 */
//	@GetMapping("/doctor")
//	public String showDoctor() {
//		
//		return "doctor-med-exam"; 
//	}
	
	/**
	 * Moderator can edit examination if doctor asked.
	 * @return
	 */
	@GetMapping("/moderator")
	public String showModerator() {
		
		return "moderator-med-exam"; 
	}
	
//	/**
//	 * Admin can everything
//	 * @return
//	 */
//	@GetMapping("/admin")
//	public String showSystems() {
//		
//		return "admin-med-exam"; 
//	}
}
