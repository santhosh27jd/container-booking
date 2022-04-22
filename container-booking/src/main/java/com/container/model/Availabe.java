package com.container.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class JSON
 * @author santhoshkumardurairaj
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Availabe {
	
	/**
	 * Variable availableSpace
	 */
	@JsonProperty
	int availableSpace;

	/**
	 * 
	 * @return
	 */
	public int getAvailableSpace() {
		return availableSpace;
	}

	/**
	 * 
	 * @param availableSpace
	 */
	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}
	
	/**
	 * Overriding method
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Availabe [availableSpace=" + availableSpace + "]";
	}
}
