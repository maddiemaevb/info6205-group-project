package model;

import java.util.*;

public class DrugRepo {

    private Map<String, Drug> drugsById;
    private Map<String, Drug> drugsByName;

    public DrugRepository() {
        this.drugsById = new HashMap<>();
        this.drugsByName = new HashMap<>();
    }

    /**
     * Load drugs into the repository
     */
    public void loadDrugs(List<Drug> drugs) {
        for (Drug drug : drugs) {
            
            if (drug.getName() != null) {
                drugsByName.put(drug.getName().toLowerCase(), drug);
            }

            if(drug.getDrugId() != null){
                drugsById.put(drug.getDrugId(), drug);
            }
            if(drug.getBrandNames() != null){
                for(String brand : drug.getBrandNames()){
                    if(brand != null && !brand.trim().isEmpty()){
                        drugsByName.put(brand.toLowerCase(), drug);
                    }
                }
            }
        }
    }

    /**
     * Get a drug by name
     */
    public Drug getDrugByName(String name) {
        if (name == null) return null;
        return drugsByName.get(name.toLowerCase());
    }

    public Drug getDrugsById(String drugId){
        if(drugId == null) return null;
        return drugsById.get(drugId);
    }

    /**
     * Check if drug exists
     */
    public boolean containsDrug(String name) {
        if (name == null) return false;
        return drugMap.containsKey(name.toLowerCase());
    }

    public boolean containsDrugId(String drugId){
        if (drugId == null) return false;
        return drugsById.containsKey(drugId);
    }

    /**
     * Get all drugs (used for Trie loading, etc.)
     */
    public Collection<Drug> getAllDrugs() {
        return drugsById.values();
    }

    /**
     * Optional: get size (useful for debugging)
     */
    public int size() {
        return drugsById.size();
    }
}
