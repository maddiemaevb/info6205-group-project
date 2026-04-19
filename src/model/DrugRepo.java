package model;

import java.util.*;

public class DrugRepo {

    private Map<String, Drug> drugMap;

    public DrugRepository() {
        this.drugMap = new HashMap<>();
    }

    /**
     * Load drugs into the repository
     */
    public void loadDrugs(List<Drug> drugs) {
        for (Drug drug : drugs) {
            if (drug.getName() != null) {
                // store using lowercase for easier searching
                drugMap.put(drug.getName().toLowerCase(), drug);
            }
        }
    }

    /**
     * Get a drug by name
     */
    public Drug getDrugByName(String name) {
        if (name == null) return null;
        return drugMap.get(name.toLowerCase());
    }

    /**
     * Check if drug exists
     */
    public boolean containsDrug(String name) {
        if (name == null) return false;
        return drugMap.containsKey(name.toLowerCase());
    }

    /**
     * Get all drugs (used for Trie loading, etc.)
     */
    public Collection<Drug> getAllDrugs() {
        return drugMap.values();
    }

    /**
     * Optional: get size (useful for debugging)
     */
    public int size() {
        return drugMap.size();
    }
}
