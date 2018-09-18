package com.ozii.klinika.controller;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;
import com.ozii.klinika.service.PatientService;

/**
 * Admin can everything
 */

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	// logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/")
	public String showAllExams(Model theModel) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// username is PESEL (11 digits)
		String username = auth.getName(); // get logged in username
		logger.info("Get exams for: " + username);
		try {
			// get patient exams from the service
			int theId = patientService.getPatientId(username);

			List<PatientExam> thePatientExams = patientService.getPatientExams(theId);
			// add the patientExams to the MVC model
			theModel.addAttribute("patientExams", thePatientExams);
			return "show-all-exams";
		} catch(EmptyResultDataAccessException e) {
			e.getStackTrace();
			logger.warning("Invalid PESEL (username)");
			return "redirect:/";
		}

	}
}
