package com.ozii.klinika.dao;

import java.util.List;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;


public interface PatientDAO {
	
	public List<Patient> getPatient();

	public void savePatient(Patient thePatient);

	public Patient getPatient(int theId);

	public void deletePatient(int theId);

	public List<Patient> searchPatient (String theSearchName);
	
	public void addPatientExam(int theId, PatientExam patientExam);
	
	public List<PatientExam> getPatientExams(int theId);

	public int getPatientId (String pesel);
	
}
