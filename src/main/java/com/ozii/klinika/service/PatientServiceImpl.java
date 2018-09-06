//package com.ozii.klinika.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ozii.klinika.dao.PatientDAO;
//import com.ozii.klinika.entity.Patient;
//
//@Service
//public class PatientServiceImpl implements PatientService {
//	
//	// need to inject patient DAO
//	@Autowired
//	private PatientDAO patientDAO;
//	
//	@Override
//	@Transactional
//	public List<Patient> getPatient() {
//		return patientDAO.getPatient();
//	}
//
//	@Override
//	@Transactional	
//	public void savePatient(Patient thePatient) {
//		patientDAO.savePatient(thePatient);
//
//	}
//
//	@Override
//	@Transactional
//	public Patient getPatient(int theId) {
//		return patientDAO.getPatient(theId);
//	}
//
//	@Override
//	@Transactional
//	public void deletePatient(int theId) {
//		patientDAO.deletePatient(theId);
//	}
//
//	@Override
//	@Transactional
//	public List<Patient> searchPatients(String theSearchName) {
//		return patientDAO.searchPatient(theSearchName);
//	}
//
//}
