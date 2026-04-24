package model;

import java.util.*;

/**
 * this repository class is for storing and retrieving drug objects
 * 
 * uses hashmaps to provide fast lookup of medications by their drug id,
 * generic name, and the brand name.
 * 
 * this class is used by the autocomplete trie and the backend services to quickly get
 * drug records without going through the whole dataset.
 */
public class DrugRepo {
    private Map<String, Drug> drugsById;
    private Map<String, Drug> drugsByName;

    public DrugRepo() {
        this.drugsById = new HashMap<>();
        this.drugsByName = new HashMap<>();
    }

    /**
     * loads a list of drug objects into the repo
     * 
     * the drugs are stored in two hashmaps as one is keyed by the drug id
     * and one is for the drug name or the brand name. so you can lookup either by name or id
     * @param drugs list of drug objects loaded from the json file
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
     * this retieves a drug by generic name or brand name
     * @param name generic name of the drug
     * @return returns the matching drug object or null if not found
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
     * checks to see if the drug exists by the generic name
     * @param name name of the drug
     * @return returns true if the drug exists or false if not
     */
    public boolean containsDrug(String name) {
        if (name == null) return false;
        return drugsByName.containsKey(name.toLowerCase());
    }

    /**
     * checks to see if the drug exists by the id
     * @param drugId the id of the drug
     * @return returns true if the drug exists or false if not
     */
    public boolean containsDrugId(String drugId){
        if (drugId == null) return false;
        return drugsById.containsKey(drugId);
    }

    /**
     * gets all of the drugs
     * @return returns all of the drugs
     */
    public Collection<Drug> getAllDrugs() {
        return drugsById.values();
    }

    /**
     * gets the size - but is mainly for debugging
     * @return returns the size by drug id
     */
    public int size() {
        return drugsById.size();
    }
}
