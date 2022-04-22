package com.container.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Entity Container
 * @author santhoshkumardurairaj
 *
 */
@Table
public class Container {

	/**
	 * id
	 */
	@PrimaryKey
	private int id;
	
	/**
	 * containerSize
	 */
	@Column(value = "container_size")
	private int containerSize;
	
	/**
	 * containerType
	 */
	@Column(value = "container_type")
	private String containerType;
	
	/**
	 * quantity
	 */
	@Column(value = "quantity")
	@Min(1)
	@Max(100)
	private int quantity;
	
	/**
	 * 
	 * GETTER and SETTER
	 */
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContainerSize() {
		return containerSize;
	}
	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
}
