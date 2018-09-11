package com.ozii.klinika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozii.klinika.dao.PatientExamDAO;
import com.ozii.klinika.entity.PatientExam;

@Service
public class PatientExamServiceImpl implements PatientExamService {
	
	// need to inject patient DAO
	@Autowired
	private PatientExamDAO patientExamDAO;

	@Override
	@Transactional
	public List<PatientExam> getPatientExam() {
		// TODO Auto-generated method stub
		return patientExamDAO.getPatientExam();
	}

	@Override
	@Transactional
	public void savePatientExam(PatientExam thePatientExam) {
		patientExamDAO.savePatientExam(thePatientExam);
		
	}

	@Override
	@Transactional
	public PatientExam getPatientExam(int theId) {
		return patientExamDAO.getPatientExam(theId);
	}

	@Override
	@Transactional
	public void deletePatientExam(int theId) {
		patientExamDAO.deletePatientExam(theId);
	}
	


}
