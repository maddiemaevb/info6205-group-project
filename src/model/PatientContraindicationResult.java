package model;

import java.util.List;

/**
 * This class encapsulates the result of checking a new drug against a patient's
 * existing medications for contraindications. It contains the name of the new
 * drug, a flag indicating whether any safety alerts were found, and a list of
 * any conflicting drugs.
 */
public class PatientContraindicationResult {

	String newDrugName;
	boolean hasSafetyAlert;
	private List<DrugConflict> conflictingDrugs;
	
	public PatientContraindicationResult(String newDrugName,
			List<DrugConflict> conflictingDrugs) {
		super();
		this.newDrugName = newDrugName;
		this.hasSafetyAlert = !conflictingDrugs.isEmpty();
		this.conflictingDrugs = conflictingDrugs;
	}

	public String getNewDrugName() {
		return this.newDrugName;
	}

	public boolean getHasSafetyAlert() {
		return this.hasSafetyAlert;
	}

	public List<DrugConflict> getConflictingDrugs() {
		return this.conflictingDrugs;
	}
}
