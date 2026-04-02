package model;

public class DietaryConflict {
	
		private String item;
		private String severity;
		private String description;
		
		public String getItem() {
			return this.item;
		}
		public String getSeverity() {
			return this.severity;
		}
		public String getDescription() {
			return this.description;
		}
		public void setItem(String item) {
			this.item = item;
		}
		public void setSeverity(String severity) {
			this.severity = severity;
		}
		public void setDescription(String description) {
			this.description = description;
		}

}
