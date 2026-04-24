package model;

import java.util.List;

/**
 * this is a wrapper class for the top level drug json structure
 * 
 * this class is to map the drugs array from drug data (json file)
 * into a java list of drug objects
 */
public class DrugDataset {

		private List <Drug> drugs;

		public List<Drug> getDrugs() {
			return this.drugs;
		}
}
