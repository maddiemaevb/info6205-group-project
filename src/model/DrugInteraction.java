/**
 * Model class representing drug - drug interaction
 */
package model;

public class DrugInteraction {
	
	private String drugId;		// ID of drug with which it has contraindications
	private String drugName;	// Name of drug with which it has contraindications
	private String severity;
	private String description;
	
	public String getDrugId() {
		return this.drugId;
	}
	public String getDrugName() {
		return this.drugName;
	}
	public String getSeverity() {
		return this.severity;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
}
