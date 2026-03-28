/**
 * Wrapper class that maps to the top-level structure of patientData.json.
 * Used by Jackson library during JSON parsing to hold the "patients" array.
 */
package model;
import java.util.List;

public class PatientDataset {
	
	private List<Patient> patients;
	
	public 	List<Patient> getPatients(){
		return this.patients;
	}
}
