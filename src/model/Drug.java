package model;

import java.util.List;

public class Drug {
	
		private String drugId;
		private String name;
		private List<String> brandNames;
		private String category;
		private List<String> warnings;
		private List<DrugInteraction> interactions;
		private List<String> allergyConflicts;
		private List<ConditionConflict> conditionConflicts;
		private List<DietaryConflict> dietaryConflicts;
		
		public String getDrugId() {
			return drugId;
		}
		public String getName() {
			return this.name;
		}
		public List<String> getBrandNames() {
			return this.brandNames;
		}
		public String getCategory() {
			return this.category;
		}
		public List<String> getWarnings() {
			return this.warnings;
		}
		public List<DrugInteraction> getInteractions() {
			return this.interactions;
		}
		public List<String> getAllergyConflicts() {
			return this.allergyConflicts;
		}
		public List<ConditionConflict> getConditionConflicts() {
			return this.conditionConflicts;
		}
		public List<DietaryConflict> getDietaryConflicts() {
			return this.dietaryConflicts;
		}
		public void setDrugId(String drugId) {
			this.drugId = drugId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setBrandNames(List<String> brandNames) {
			this.brandNames = brandNames;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public void setWarnings(List<String> warnings) {
			this.warnings = warnings;
		}
		public void setInteractions(List<DrugInteraction> interactions) {
			this.interactions = interactions;
		}
		public void setAllergyConflicts(List<String> allergyConflicts) {
			this.allergyConflicts = allergyConflicts;
		}
		public void setConditionConflicts(List<ConditionConflict> conditionConflicts) {
			this.conditionConflicts = conditionConflicts;
		}
		public void setDietaryConflicts(List<DietaryConflict> dietaryConflicts) {
			this.dietaryConflicts = dietaryConflicts;
		}
		
		
}
