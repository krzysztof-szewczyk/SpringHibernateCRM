package com.ozii.klinika.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ozii.klinika.entity.Patient;
import com.ozii.klinika.entity.PatientExam;

@Repository
public class PatientDAOImpl implements PatientDAO {

	@Autowired
	private SessionFactory sessionFactory;

	Logger logger = Logger.getLogger(getClass().getName());

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

		Query<Patient> theQuery = null;

		// search
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search by firstName or lastName
			theQuery = currentSession.createQuery(
					"from Patient where lower(firstName) like :theName or lower(lastName) like :theName",
					Patient.class);

			// set parameter
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {

			// show all patients if theSearchName is empty
			theQuery = currentSession.createQuery("from Patient", Patient.class);
		}

		// execute
		List<Patient> patients = theQuery.getResultList();

		return patients;
	}

	@Override
	public void addPatientExam(int theId, PatientExam patientExam) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get patient
		Patient thePatient = currentSession.get(Patient.class, theId);
		List<PatientExam> patientExams = thePatient.getPatientExams();

		if (patientExams == null) {
			patientExams = new ArrayList<>();
		}

		patientExams.add(patientExam);

		patientExam.setPatient(thePatient);

	}

	@Override
	public List<PatientExam> getPatientExams(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<PatientExam> theQuery = null;
		theQuery = currentSession.createQuery("from PatientExam where patient.id= :id", PatientExam.class);

		// set parameter
		theQuery.setParameter("id", theId);
		//

		// execute
		List<PatientExam> patientExams = theQuery.getResultList();

		return patientExams;
	}

	@Override
	public int getPatientId(String pesel) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<?> theQuery = null;
		logger.info("before query");
		theQuery = currentSession.createQuery("Select id from Patient where pesel= :pesel");

		// set parameter
		theQuery.setParameter("pesel", pesel);
		
		int theId = (int) theQuery.getSingleResult();
		
		return theId;
	}

}
