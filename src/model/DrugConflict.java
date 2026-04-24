package model;

/**
 * this class represnents a drug to drug interacton
 * 
 * each drug conflict stores the id and the name of the interacting drug
 * along with the severity and the description of the clinical risk.
 */
public class DrugConflict {
	
	private String drugId;
	private String drugName;
	private String severity;
	private String description;
	
	/**
	 * getting the drug id of the conflict
	 * @return returns the interacting drug id
	 */
	public String getDrugId() {
		return this.drugId;
	}

	/**
	 * getting the drug name of the conflict
	 * @return returns the name of the interacting drug
	 */
	public String getDrugName() {
		return this.drugName;
	}

	/**
	 * getting the severity of the conflict
	 * @return returns the severity of the interaction
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
	 * setting the drug id of the conflict
	 * @param drugId the drug id in the conflict
	 */
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	/**
	 * setting the drug name in the conflict
	 * @param drugName the name of the drug
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	/**
	 * setting the severity of the drug conflict
	 * @param severity the severity of the conflict
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
