/**
 * Class representing patient as java object
 */
package model;

import java.util.List;

public class Patient {
	
	/* Patient attributes mapped from patientData JSON */
	private String patientId;
	private String name;
	private String dateOfBirth;
	private int age;
	private String sex;
	private List<String> medications;
	private List<String> allergies;
	private List<String> conditions;
	private List<String> dietaryHabits;
	private String clinicalNotes;
	
	/* Accesor methods */
	public String getPatientId() {
		return patientId;
	}
	public String getName() {
		return name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public List<String> getMedications() {
		return medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public List<String> getDietaryHabits() {
		return dietaryHabits;
	}
	public String getClinicalNotes() {
		return clinicalNotes;
	}	
}
