package com.ozii.klinika.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Entity // Tells Hibernate to make a table out of this class
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	@Pattern(regexp = "^[a-zA-Z]{1,45}$", message = "use only letters (1-45)")
	private String firstName;

	@Column(name = "last_name")
	@Pattern(regexp = "^[a-zA-Z]{1,45}$", message = "use only letters (1-45)")
	private String lastName;

	@Column(name = "gender")
	@NotNull(message = "is required")
	private String gender;

	@Column(name = "pesel")
	@NotNull(message = "is required")
	@Pattern(regexp = "^\\d{11}$", message = "use 11 digits")
	private String pesel;
	
	//By default, no operations are cascade
	//mappedBy refers to "patient" property in "patientExam" class
	@OneToMany(mappedBy="patient", cascade=CascadeType.ALL)
	private List<PatientExam> patientExams;

	public Patient() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the pesel
	 */
	public String getPesel() {
		return pesel;
	}

	/**
	 * @param pesel the pesel to set
	 */
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	/**
	 * @return the patientExams
	 */
	public List<PatientExam> getPatientExams() {
		return patientExams;
	}

	/**
	 * @param patientExams the patientExams to set
	 */
	public void setPatientExams(List<PatientExam> patientExams) {
		this.patientExams = patientExams;
	}



	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", pesel=" + pesel + ", patientExams=" + patientExams + "]";
	}



}
