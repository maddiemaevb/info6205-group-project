package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepo {

    private Map<String, Patient> patientsById;

    public PatientRepo() {
        this.patientsById = new HashMap<>();
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
            }
        }
    }

    public Patient getPatientById(String patientId) {
        if (patientId == null) {
            return null;
        }
        return patientsById.get(patientId);
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