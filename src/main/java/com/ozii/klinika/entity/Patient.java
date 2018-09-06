package com.ozii.klinika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Tells Hibernate to make a table out of this class
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;
	
	@Column(name = "pesel")
	private String pesel;
	
	@Column(name = "temperature")
	private String temperature;
	
	@Column(name = "type_of_examination")
	private String typeOfExamination;
	
	@Column(name = "date")
	private String date;

	public Patient() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param firstName
	 *            the firstName to set
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
	 * @param lastName
	 *            the lastName to set
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
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the typeOfExamination
	 */
	public String getTypeOfExamination() {
		return typeOfExamination;
	}

	/**
	 * @param typeOfExamination the typeOfExamination to set
	 */
	public void setTypeOfExamination(String typeOfExamination) {
		this.typeOfExamination = typeOfExamination;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", pesel=" + pesel + ", temperature=" + temperature + ", typeOfExamination=" + typeOfExamination
				+ ", date=" + date + "]";
	}


}
