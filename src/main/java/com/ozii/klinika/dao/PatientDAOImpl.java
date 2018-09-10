package com.ozii.klinika.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ozii.klinika.entity.Patient;

@Repository
public class PatientDAOImpl implements PatientDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Patient> getPatient() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Patient> theQuery = currentSession.createQuery("from Patient order by lastName", Patient.class);
		
		// execute query and get result list
		List<Patient> patients = theQuery.getResultList(); 
		
		return patients;
	}

	@Override
	public void savePatient(Patient thePatient) {
		 
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save
		currentSession.saveOrUpdate(thePatient);
		
	}

	@Override
	public Patient getPatient(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get patient
		Patient thePatient = currentSession.get(Patient.class, theId);
		
		return thePatient;
	}

	@Override
	public void deletePatient(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete
		currentSession.delete(getPatient(theId));
		
	}

	@Override
	public List<Patient> searchPatient(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;
		
		//search
		if(theSearchName != null && theSearchName.trim().length() > 0) {
			
			//search by firstName or lastName
			theQuery = currentSession.createQuery("from Patient where lower(firstName) like :theName or lower(lastName) like :theName", Patient.class);
			
			// set parameter
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
			
		} else {
			
			//show all patients if theSearchName is empty
			theQuery = currentSession.createQuery("from Patient", Patient.class);
		}
		
		//execute
		List<Patient> patients = theQuery.getResultList();
		
		return patients;
	}

}
