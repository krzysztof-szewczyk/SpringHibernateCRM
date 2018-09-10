package com.ozii.klinika.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;

@Service
public interface PatientExamService {

	public List<PatientExam> getPatientExam();

	public void savePatientExam(PatientExam thePatientExam);

	public PatientExam getPatientExam(int theId);

	public void deletePatient(int theId);
}
