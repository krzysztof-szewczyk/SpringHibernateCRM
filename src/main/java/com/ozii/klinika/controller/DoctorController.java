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
	

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PatientExamService patientExamService;

	/**
	 * Default view.
	 * @return doctor-med-exam.jsp
	 */
	@GetMapping("/")
	public String showDoctor() {
	// /WEB-INF/view/doctor.jsp
		return "doctor-med-exam";
	}

	/**
	 * Show Patient CRM list.
	 * Available actions with existing patients:
	 * DOCTOR: update, addExam, allExams, showFormForUpdateExam
	 * MODERATOR: none
 	 * ADMIN: the same as DOCTOR and deletePatient
 	 * 
 	 * @param theModel
 	 * 
	 * @return list-patients.jsp
 	 */
	@GetMapping("/list")
	public String listPatient(Model theModel) {
		// get patient from the service
		List<Patient> thePatient = patientService.getPatient();

		// add the patient to the MVC model
		theModel.addAttribute("patients", thePatient);

		return "list-patients";
	}

	/**
	 * Adding a new Patient to list.
	 * @param theModel
	 * @return patient-form.jsp
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Patient thePatient = new Patient();
		theModel.addAttribute("patient", thePatient);

		return "patient-form";
	}

	/**
	 * Adding a new exam for existing patient.
	 * @param theModel
	 * @return patient-exam-form.jsp
	 */
	@GetMapping("/showFormForAddExam")
	public String showFormForAddExam(Model theModel) {

		PatientExam thePatientExam = new PatientExam();
		theModel.addAttribute("patientExam", thePatientExam);
		// name, value

		return "patient-exam-form";
	}
	
	/**
	 * Show all exams that the patient has.
	 * @param theModel
	 * @param theId Patient's ID key.
	 * @return show-all-exams.jsp
	 */
	@GetMapping("/showAllExams")
	public String showAllExams(Model theModel, @RequestParam("patientID") int theId) {
		// get patient from the service
		List<PatientExam> thePatientExams = patientService.getPatientExams(theId);

		// add the patient to the MVC model
		theModel.addAttribute("patientExams", thePatientExams);

		return "show-all-exams";
	}

	/**
	 * Method valid the patient, then save him in the database.
	 * @param thePatient
	 * @param theBindingResult
	 * @return redirect:/doctor/list
	 */
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

	/**
	 * Method valid the patient's exam, then save it.
	 * @param thePatientExam
	 * @param theBindingResult
	 * @param theId Patient's ID key.
	 * @return redirect:/doctor/list
	 */
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

	/**
	 * Show form, where patient's props correction is possible.
	 * @param theId Patient's ID key.
	 * @param theModel
	 * @return patient-form.jsp
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("patientID") int theId, Model theModel) {
		// get patient from the DB
		Patient thePatient = patientService.getPatient(theId);
		// set patient as a model attribute to pre-populate the form
		theModel.addAttribute("patient", thePatient);
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

	/**
	 * Only ROLE_ADMIN is able to delete patients.
	 * @param theId Patient's ID key.
	 * @param theModel
	 * @return redirect:/doctor/list
	 */
	@GetMapping("/delete")
	public String deletePatient(@RequestParam("patientID") int theId, Model theModel) {

		patientService.deletePatient(theId);

		return "redirect:/doctor/list";
	}

	/**
	 * Method research database in order to find patients, whose first name or last
	 * name contains the search name
	 * 
	 * @param theSearchName
	 *            The String signifying a part of patient's first name or last name
	 * @param theModel
	 * @return list-patients.jsp
	 */
	@PostMapping("/search")
	public String searchPatient(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		// search patients from the service
		List<Patient> thePatients = patientService.searchPatients(theSearchName);

		// add the patients to the model
		theModel.addAttribute("patients", thePatients);
		return "list-patients";
	}

}
