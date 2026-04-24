package model;

/**
 * this class represents a conflict between a drug and a dietary habit
 * 
 * this class is used when a medication may interact with something in the patients diet
 */
public class DietaryConflict {
	
		private String item;
		private String severity;
		private String description;
		
		/**
		 * getting the item from the conflict
		 * @return returns the item
		 */
		public String getItem() {
			return this.item;
		}

		/**
		 * getting the severity of the conflict
		 * @return returns the severity
		 */
		public String getSeverity() {
			return this.severity;
		}

		/**
		 * getting the description of the conflict
		 * @return returns the description
		 */
		public String getDescription() {
			return this.description;
		}

		/**
		 * setting the item from the conflict
		 * @param item the item
		 */
		public void setItem(String item) {
			this.item = item;
		}

		/**
		 * setting the severity of the conflict
		 * @param severity the severity
		 */
		public void setSeverity(String severity) {
			this.severity = severity;
		}

		/**
		 * setting the description of the conflict
		 * @param description the description
		 */
		public void setDescription(String description) {
			this.description = description;
		}

}
