package com.ozii.klinika.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="patient_exam")
public class PatientExam {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="temperature")
	@Pattern(regexp = "^(\\d+(\\.\\d+)?)$", message = "use only digits and use . as separator")
	private String temperature;
	
	@Column(name="type_of_examination")
	private String typeOfExamination;
	
	@Column(name="date")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@NotNull(message="Set date")
	@Past(message="Set past date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;

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
	 * @return the typeOfExam
	 */
	public String getTypeOfExamination() {
		return typeOfExamination;
	}

	/**
	 * @param typeOfExam the typeOfExam to set
	 */
	public void setTypeOfExam(String typeOfExamination) {
		this.typeOfExamination = typeOfExamination;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "PatientExam [id=" + id + ", temperature=" + temperature + ", typeOfExam=" + typeOfExamination + ", date="
				+ date + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
	
	
}
