package model;

/**
 * this class represents a conflict between a drug and a patient
 * medical condition.
 * 
 * it also stores the condition name, the severity and the reason why the
 * drug might be unsafe for the patient to take
 */
public class ConditionConflict {
	
		private String condition;
		private String severity;
		private String description;
		
		/**
		 * getting the condition of the conflict
		 * @return condition name
		 */
		public String getCondition() {
			return this.condition;
		}

		/**
		 * getting the severity of the conflict
		 * @return the severity level
		 */
		public String getSeverity() {
			return this.severity;
		}

		/**
		 * getting the description of why its unsafe
		 * @return returns the description
		 */
		public String getDescription() {
			return this.description;
		}

		/**
		 * sets the condition
		 * @param condition the condition of the conflict
		 */
		public void setCondition(String condition) {
			this.condition = condition;
		}

		/**
		 * setting the severity
		 * @param severity the severity of the conflict
		 */
		public void setSeverity(String severity) {
			this.severity = severity;
		}

		/**
		 * setting the description
		 * @param description the description of the conflict
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		
}
