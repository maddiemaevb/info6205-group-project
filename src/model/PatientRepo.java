package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepo {

    private Map<String, Patient> patientsById;
    private Map<String, Patient> patientsByName;

    public PatientRepo() {
    	// One hashmap stores patients by their unique patientId, and the other stores patients by their name for quick lookup by name.
        this.patientsById = new HashMap<>();
        this.patientsByName = new HashMap<>(); 
    }

    /**
     * Loads patients into the repository using patientId as the key.
     */
    public void loadPatients(List<Patient> patients) {
        if (patients == null) {
            return;
        }

        for (Patient patient : patients) {
            if (patient != null && patient.getPatientId() != null) {
                patientsById.put(patient.getPatientId(), patient);
                patientsByName.put(patient.getName().toLowerCase(), patient);
            }
        }
    }
    
    public Patient getPatientById(String patientId) {
        if (patientId == null) {
            return null;
        }
        return patientsById.get(patientId);
    }
    
    // To get patients by name
    public Patient getPatientByName(String name) {
        if (name == null) return null;
        return patientsByName.get(name.toLowerCase().trim());
    }

    public Collection<Patient> getAllPatients() {
        return patientsById.values();
    }

    public boolean containsPatient(String patientId) {
        if (patientId == null) {
            return false;
        }
        return patientsById.containsKey(patientId);
    }


    public int size() {
        return patientsById.size();
    }
}