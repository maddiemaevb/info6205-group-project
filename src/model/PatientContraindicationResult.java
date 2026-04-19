package model;

import java.util.List;

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

	public boolean hasSafetyAlert() {
		return this.hasSafetyAlert;
	}

	public List<DrugConflict> getConflictingDrugs() {
		return this.conflictingDrugs;
	}
}
