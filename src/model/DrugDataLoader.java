package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class DrugDataLoader {
	
	private final List<Drug> drugs;
	
	public DrugDataLoader (String filePath) throws IOException {
		
		/* Parse JSON file into Java object */
		ObjectMapper mapper = new ObjectMapper();
		
		/* Object mapper reads jason file and maps it to attribute sif java object */
		DrugDataset dataset = mapper.readValue(new File(filePath), DrugDataset.class);
		
		/* Validation for file reference error*/
		if(dataset.getDrugs()==null) {
			throw new IOException("Error in reading drug data file"+ filePath);
		}
		
		/* Validation for empty file */
		if(dataset.getDrugs().isEmpty()) {
			throw new IOException("Drug data file is empty"+ filePath);
		}
		
		/* Storing the data as unmodifiable list */
		this.drugs = Collections.unmodifiableList(dataset.getDrugs());
		
		System.out.println("DrugDataLoader: loaded " + drugs.size() + " drugs.");
	}
	
	public List<Drug> getDrugs(){
		return drugs;
	}
	
}
