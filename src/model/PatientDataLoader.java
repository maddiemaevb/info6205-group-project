/**
 *  Class that parses JSON data into Java object
 */
package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;
import java.util.Collections;
import java.util.List;


public class PatientDataLoader{
	
	private final List<Patient> patients;
	
	public PatientDataLoader (String filePath) throws IOException {
		
		/* Parse JSON file into Java object */
		ObjectMapper mapper = new ObjectMapper();
		
		/* Object mapper reads jason file and maps it to attribute sif java object */
		PatientDataset dataset = mapper.readValue(new File(filePath), PatientDataset.class);
		
		/* Validation for empty file */
		if(dataset.getPatients().isEmpty()) {
			throw new IOException("Patient data file is empty"+ filePath);
		}
		
		/* Validation for file reference error*/
		if(dataset.getPatients()==null) {
			throw new IOException("Error is reading patient data file"+ filePath);
		}
		
		/* Storing the data as unmodifiable list */
		this.patients = Collections.unmodifiableList(dataset.getPatients());
		
		System.out.println("PatientDataLoader: loaded " + patients.size() + " patients.");
	}
	
	public List<Patient> getPatients(){
		return patients;
	}
	

}
