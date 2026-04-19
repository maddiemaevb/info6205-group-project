package main;

import model.DrugDataLoader;
import service.DrugContraindicationService;
import model.ContraindicationResult;
import model.PatientContraindicationResult;
import java.io.IOException;
import java.util.List;
import model.DrugConflict;


public class TestServices {
 
 public static void main(String[] args) throws IOException {
     
	 try {
		    DrugDataLoader drugLoader = new DrugDataLoader("resources/drugData.json");
		    DrugContraindicationService service = new DrugContraindicationService(drugLoader.getDrugs());
		    
		    ContraindicationResult result = service.getcontraIndications("warfarin");
		    System.out.println("Drug: " + result.getDrugName());
		    System.out.println("Interactions: " + result.getDrugInteraction().size());
		    System.out.println("Condition Conflicts: " + result.getConditionInteraction().size());
		    System.out.println("Dietary Conflicts: " + result.getDietaryInteraction().size());
		    System.out.println("Allergy Conflicts: " + result.getAllergyInteraction().size());
		    
		 // Patient is currently on aspirin (D002) and metformin (D006)
			 List<String> patientMeds = List.of("D002", "D006");
			 PatientContraindicationResult patientResult = service.checkAgainstPatient("warfarin", patientMeds);

			 System.out.println("Safety Alert: " + patientResult.hasSafetyAlert());
			 System.out.println("Conflicting drugs: " + patientResult.getConflictingDrugs().size());
			 for(DrugConflict d : patientResult.getConflictingDrugs()) {
			     System.out.println("  [" + d.getSeverity() + "] " + d.getDrugName() + ": " + d.getDescription());
			 }
	 } 
	 
	 catch (IOException e) {
		    System.out.println("Error loading data: " + e.getMessage());
		}
	 
	
    
 }
 
}
