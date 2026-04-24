package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * this class represents a single medication in the medical alert system
 * 
 * a drug object stores identifying information such as drug id, the generic name,
 * brand names, categories, warnings, and known conflicts. the conflict fields are
 * loaded from the JSON dataset.
 * 
 * this is also used for the main drug node in the graph interaction model
 * 
 * each drug is stored in a list of interacting drugs, so it is similar to an adjacency list
 */
public class Drug {
	
		private String drugId;
		private String name;
		private List<String> brandNames;
		private String category;
		private List<String> warnings;
		@JsonProperty("interactions") 
		private List<DrugConflict> drugConflicts;
		private List<String> allergyConflicts;
		private List<ConditionConflict> conditionConflicts;
		private List<DietaryConflict> dietaryConflicts;
		
		/**
		 * getting the id of the drug
		 * @return returns the drug id
		 */
		public String getDrugId() {
			return drugId;
		}

		/**
		 * getting the name of the drug
		 * @return returns the drug name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * getting the brand name of the drug
		 * @return returns the drug brand name
		 */
		public List<String> getBrandNames() {
			return this.brandNames;
		}

		/**
		 * getting the category of the drug
		 * @return returns the drug category
		 */
		public String getCategory() {
			return this.category;
		}

		/**
		 * getting the warning for the drug
		 * @return returns the drug warning
		 */
		public List<String> getWarnings() {
			return this.warnings;
		}

		/**
		 * getting the conflicts of the drug
		 * @return returns the drug conflicts
		 */
		public List<DrugConflict> getDrugConflicts() {
			return this.drugConflicts;
		}

		/**
		 * getting the allergy conflicts of the drug
		 * @return returns the drug allergy conflicts
		 */
		public List<String> getAllergyConflicts() {
			return this.allergyConflicts;
		}

		/**
		 * getting the condition conflicts of the drug
		 * @return returns the drug condition conflicts
		 */
		public List<ConditionConflict> getConditionConflicts() {
			return this.conditionConflicts;
		}

		/**
		 * getting the dietary conflicts of the drug
		 * @return returns the drug dietary conflicts
		 */
		public List<DietaryConflict> getDietaryConflicts() {
			return this.dietaryConflicts;
		}

		/**
		 * setting the drug id
		 * @param drugId the id of the drug
		 */
		public void setDrugId(String drugId) {
			this.drugId = drugId;
		}

		/**
		 * setting the drug name
		 * @param name the name of the drug
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * setting the drug brand name
		 * @param brandNames the brand name of the drug
		 */
		public void setBrandNames(List<String> brandNames) {
			this.brandNames = brandNames;
		}

		/**
		 * setting the drug category
		 * @param category the category of the drug
		 */
		public void setCategory(String category) {
			this.category = category;
		}

		/**
		 * setting the warnings
		 * @param warnings the warnings of the drug
		 */
		public void setWarnings(List<String> warnings) {
			this.warnings = warnings;
		}

		/**
		 * setting the drug conflicts
		 * @param drugConflicts the conflicts of the drug
		 */
		public void setDrugConflicts(List<DrugConflict> drugConflicts) {
			this.drugConflicts = drugConflicts;
		}

		/**
		 * setting the allergy conflicts
		 * @param allergyConflicts the allergy conflicts of the drug
		 */
		public void setAllergyConflicts(List<String> allergyConflicts) {
			this.allergyConflicts = allergyConflicts;
		}

		/**
		 * setting the condition conflicts
		 * @param conditionConflicts the condition conflicts of the drug
		 */
		public void setConditionConflicts(List<ConditionConflict> conditionConflicts) {
			this.conditionConflicts = conditionConflicts;
		}

		/**
		 * setting the dietart conflict
		 * @param dietaryConflicts the dietary conflicts of the drug
		 */
		public void setDietaryConflicts(List<DietaryConflict> dietaryConflicts) {
			this.dietaryConflicts = dietaryConflicts;
		}
		
		/**
		 * compares the two drug objects based on their unique drug id
		 * 
		 * two drug objects are considered equal if they have the same id
		 */
		@Override
		public boolean equals(Object object) {
			if(this==object) {
				return true;
			}
			if(!(object instanceof Drug)) {
				return false;
			}
			Drug other = (Drug) object;
			return this.getDrugId().equals(other.getDrugId());
		}
		
		/**
		 * this generates a hash code based on the drug id
		 * 
		 * allows drug objects to be used correctly in hash based collections
		 */
		@Override
		public int hashCode() {
			return drugId.hashCode();
		}
}
