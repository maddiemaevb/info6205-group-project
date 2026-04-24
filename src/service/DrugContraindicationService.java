package service;

import model.Drug;
import model.DrugConflict;
import model.PatientContraindicationResult;
import model.ConditionConflict;
import model.DietaryConflict;
import model.ContraindicationResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class DrugContraindicationService {
		
		private final Map<String, List<DrugConflict>> graph;
		private final Map<String, Drug> drugsByName;
		
		public DrugContraindicationService(List<Drug> drugs) {
			this.graph = new HashMap<>();
			this.drugsByName = new HashMap<>();
			// storing drug and its interactions with other drugs in a graph with drugID as key and list of drugs it
			// interacts negatively with as values
			for(Drug drug:drugs) {
				graph.put(drug.getDrugId(), drug.getDrugConflicts());
			}
			// storing drugs in a hashmap with drug name as key and drug object as the value
			for(Drug drug:drugs) {
				drugsByName.put(drug.getName().toLowerCase(), drug);			
			}
			System.out.println("DrugContraindicationService: initialised with " + drugsByName.size() + " drugs.");
		}
		
		/**
		 * Returns all conflicts for a given drug if it exists
		 */
		public ContraindicationResult getcontraIndications(String drugName) {
			Drug drug = drugsByName.get(drugName.toLowerCase().trim());
			if(drug == null) {
				return new ContraindicationResult(drugName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			}
			List<DrugConflict> interactions = drug.getDrugConflicts();
			List<ConditionConflict> conditionConflicts = drug.getConditionConflicts();
			List<DietaryConflict> dietaryConflicts = drug.getDietaryConflicts();
			List<String> allergyConflicts = drug.getAllergyConflicts();
			if (interactions == null) 
				interactions = new ArrayList<>();
			if (conditionConflicts == null) 
				conditionConflicts = new ArrayList<>();
			if (dietaryConflicts == null) 
				dietaryConflicts = new ArrayList<>();
			if (allergyConflicts == null) 
				allergyConflicts = new ArrayList<>();
			return new ContraindicationResult(drug.getName(), interactions, conditionConflicts, dietaryConflicts, allergyConflicts);
		}
		
		/**
		 * @param newDrugName is the drug against which patient's existing habits, conditions, medications will be matched to check contraindication 
		 * @param patientDrugIds is the list of drug IDs for patient's existing medications
		 * @return object of PatientContraindicationResult containing drugs with conflicts
		 */
		
		public PatientContraindicationResult checkAgainstPatient(String newDrugName, List<String> patientDrugIds) {
			Drug newDrug = drugsByName.get(newDrugName.toLowerCase().trim());
			if(newDrug == null) {
				return new PatientContraindicationResult(newDrugName, new ArrayList<>());
			}
			// Using a HashSet for patientDrugIds to allow for O(1) lookups when checking for interactions
			Set<String> patientDrugSet = new HashSet<>(patientDrugIds);
			// List to store any conflicting drugs found during the check
			List<DrugConflict> conflicting = new ArrayList<>();
			// Retrieve all interactions for the new drug from the graph. If there are no interactions, return an empty result.
			List<DrugConflict> allInteractions =  graph.get(newDrug.getDrugId());
			if(allInteractions == null) {
				return new PatientContraindicationResult(newDrugName, new ArrayList<>());
			}
			//Check each interaction of the new drug against the patient's medication set, 
			//If the interacting drug is in the patient's current medications, add it as a conflict
			for(DrugConflict interaction: allInteractions) {
				if(patientDrugSet.contains(interaction.getDrugId())) {
					conflicting.add(interaction);
				}
			}
			
			return new PatientContraindicationResult(newDrugName, conflicting);
		}
		
}