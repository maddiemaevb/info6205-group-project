/*
 * wrapper class to return all contraindication against a drug
 */
package model;
import java.util.List;

public class ContraindicationResult {
		
	private String drugName;
	private List<DrugConflict> drugConflict;
	private List<ConditionConflict> conditionInteraction;
	private List<DietaryConflict> dietaryInteraction;
	private List<String> allergyInteraction;
		
		public ContraindicationResult (String drugName, List<DrugConflict> drugConflict, 
				List<ConditionConflict> conditionInteraction, List<DietaryConflict> dietaryInteraction, 
				List<String> allergyInteraction) {
			this.drugName = drugName;
			this.drugConflict = drugConflict;
			this.conditionInteraction = conditionInteraction;
			this.dietaryInteraction = dietaryInteraction;
			this.allergyInteraction = allergyInteraction;
		}
		
		public String getDrugName() {
			return drugName;
		}

		public List<DrugConflict> getDrugInteraction() {
			return drugConflict;
		}

		public List<ConditionConflict> getConditionInteraction() {
			return conditionInteraction;
		}

		public List<DietaryConflict> getDietaryInteraction() {
			return dietaryInteraction;
		}
		

		public List<String> getAllergyInteraction() {
			return allergyInteraction;
		}

		// checks if the drug has any form of contraindication
		public boolean hasAnyContraIndication() {
			return !this.drugConflict.isEmpty() || !this.dietaryInteraction.isEmpty() || 
					!this.conditionInteraction.isEmpty() || !this.allergyInteraction.isEmpty(); 
		}
}
