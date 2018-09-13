package com.ozii.klinika.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;
import com.ozii.klinika.service.PatientExamService;
import com.ozii.klinika.service.PatientService;
import com.ozii.klinika.user.Customer;

/**
 * Doctor can create and save patient's medical examination.
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {
	// need to inject to this controller
	@Autowired
	private PatientService patientService;
	@Autowired
	private PatientExamService patientExamService;

	@GetMapping("/")
	public String showDoctor() {
		return "doctor-med-exam"; // /WEB-INF/view/doctor.jsp
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("customer", new Customer());
		return "registration-form";
	}

	@GetMapping("/list")
	public String listPatient(Model theModel) {
		// get patient from the service
		List<Patient> thePatient = patientService.getPatient();

		// add the patient to the MVC model
		theModel.addAttribute("patients", thePatient);

		return "list-patients";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Patient thePatient = new Patient();
		theModel.addAttribute("patient", thePatient);
		// name, value

		return "patient-form";
	}

	@GetMapping("/showFormForAddExam")
	public String showFormForAddExam(Model theModel) {

		PatientExam thePatientExam = new PatientExam();
		theModel.addAttribute("patientExam", thePatientExam);
		// name, value

		return "patient-exam-form";
	}
	
	@GetMapping("/showAllExams")
	public String showAllExams(Model theModel, @RequestParam("patientID") int theId) {
		// get patient from the service
		List<PatientExam> thePatientExams = patientService.getPatientExams(theId);

		// add the patient to the MVC model
		theModel.addAttribute("patientExams", thePatientExams);

		return "show-all-exams";
	}

	@PostMapping("/savePatient")
	public String savePatient(@Valid @ModelAttribute("patient") Patient thePatient, BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "patient-form";
		} else {
			// save the patient using our service
			patientService.savePatient(thePatient);
			return "redirect:/doctor/list";
		}
	}

	@PostMapping("/savePatientExam")
	public String savePatientExam(@Valid @ModelAttribute("patientExam") PatientExam thePatientExam,
			BindingResult theBindingResult, @RequestParam("patientID") int theId) {

		if (theBindingResult.hasErrors()) {
			return "patient-exam-form";
		} else {
			// save the patient exam using our service
			patientExamService.savePatientExam(thePatientExam);
			patientService.addPatientExam(theId, thePatientExam);
			return "redirect:/doctor/list";
		}
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("patientID") int theId, Model theModel) {
		// get patient from the DB
		Patient thePatient = patientService.getPatient(theId);
		// set patient as a model attribute to pre-populate the form
		theModel.addAttribute("patient", thePatient);
		// send over to our form

		// behind the scenes Spring MVC will gona pre-populate the fields:
		// patient.getFirstName(), patient.getLastName(). etc and setters.setters..
		return "patient-form";
	}

	// @GetMapping("/showFormForUpdateExam")
	// public String showFormForUpdateExam(@RequestParam("patientID") int theId,
	// Model theModel) {
	// // get patient from the DB
	// Patient thePatient = patientService.getPatient(theId);
	// // set patient as a model attribute to pre-populate the form
	// theModel.addAttribute("patient", thePatient);
	// // send over to our form
	//
	// // behind the scenes Spring MVC will gona pre-populate the fields:
	// // patient.getFirstName(), patient.getLastName(). etc and setters.setters..
	// return "patient-exam-form";
	// }

	@GetMapping("/delete")
	public String deletePatient(@RequestParam("patientID") int theId, Model theModel) {

		patientService.deletePatient(theId);

		return "redirect:/doctor/list";
	}

	// dziala tutaj i GET i POST
	@PostMapping("/search")
	public String searchPatient(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		// search patients from the service
		List<Patient> thePatients = patientService.searchPatients(theSearchName);

		// add the patients to the model
		theModel.addAttribute("patients", thePatients);
		return "list-patients";
	}

}
