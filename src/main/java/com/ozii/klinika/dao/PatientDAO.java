package com.ozii.klinika.dao;

import java.util.List;

import com.ozii.klinika.entity.Patient;


public interface PatientDAO {
	public List<Patient> getPatient();

	public void savePatient(Patient thePatient);

	public Patient getPatient(int theId);

	public void deletePatient(int theId);

	public List<Patient> searchPatient(String theSearchName);
}
