package service;

//service/DrugHashMap.java
import java.util.Map;
import java.util.List;

import model.Drug;

import java.util.ArrayList;
import java.util.HashMap;

public class DrugHashMap {
 
 private Map<String, Drug> drugMap;
 
 // Constructor
 public DrugHashMap(List<Drug> drugs) {
     this.drugMap = new HashMap<>();
     buildHashMap(drugs);
 }
 
 // Build the HashMap Id is the key and Drug object is the value
 private void buildHashMap(List<Drug> drugs) {
     for (Drug drug : drugs) {
         drugMap.put(drug.getDrugId(), drug);
     }
 }
 

 /**
  * O(1) lookup by drug ID
  * Business logic: Fast retrieval of drug information
  */
 public Drug getDrugById(String drugId) {
     return drugMap.get(drugId);
 }
 
 /**
  * Check if drug exists
  */
 public boolean containsDrug(String drugId) {
     return drugMap.containsKey(drugId);
 }
 
 /**
  * Get all drugs
  */
 public List<Drug> getAllDrugs() {
     return new ArrayList<>(drugMap.values());
 }
 
 /**
  * Get total number of drugs
  */
 public int getDrugCount() {
     return drugMap.size();
 }
}
