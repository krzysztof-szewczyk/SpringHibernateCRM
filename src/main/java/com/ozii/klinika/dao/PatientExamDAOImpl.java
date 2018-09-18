package com.ozii.klinika.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;

@Repository
public class PatientExamDAOImpl implements PatientExamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientExam> getPatientExam() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<PatientExam> theQuery = currentSession.createQuery("from PatientExam", PatientExam.class);
		
		// execute query and get result list
		List<PatientExam> patientExams = theQuery.getResultList(); 
		
		return patientExams;
	}

	@Override
	public void savePatientExam(PatientExam thePatientExam) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save
		currentSession.saveOrUpdate(thePatientExam);
	}

	@Override
	public PatientExam getPatientExam(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatientExam(int theId) {
		// TODO Auto-generated method stub

	}

}
