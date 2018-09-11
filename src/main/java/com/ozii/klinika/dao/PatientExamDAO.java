package com.ozii.klinika.dao;

import java.util.List;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;


public interface PatientExamDAO {
	
	public List<PatientExam> getPatientExam();

	public void savePatientExam (PatientExam thePatientExam);

	public PatientExam getPatientExam (int theId);

	public void deletePatientExam (int theId);
	
}
