package com.container.model;

import java.sql.Timestamp;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Entity Booking
 * @author santhoshkumardurairaj
 *
 */
@Table
public class Booking {
	/**
	 * bookingRefId
	 */
	@PrimaryKey(value = "booking_ref_id")
	private int bookingRefId = 957000001;
	
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
	 * origin
	 */
	@Column(value = "origin")
	private String origin;
	
	/**
	 * destination
	 */
	@Column(value = "destination")
	private String destination;
	
	/**
	 * quantity
	 */
	@Column(value = "quantity")
	private int quantity;
	
	/**
	 * timeStamp
	 */
	@Column(value = "time_stamp")
	private Timestamp timeStamp;
	
	
	/**
	 * GETTER and SETTER
	 */
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getBookingRefId() {
		return bookingRefId;
	}
	public void setBookingRefId(int bookingRefId) {
		this.bookingRefId = bookingRefId;
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
