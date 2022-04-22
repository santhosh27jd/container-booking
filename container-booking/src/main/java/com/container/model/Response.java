package com.container.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class Response
 * @author santhoshkumardurairaj
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

	/**
	 * Booking Reference Id
	 */
	int bookingRefId;

	/**
	 * getBookingRefId
	 * @return
	 */
	public int getBookingRefId() {
		return bookingRefId;
	}

	/**
	 * setBookingRefId
	 * @param bookingRefId
	 */
	public void setBookingRefId(int bookingRefId) {
		this.bookingRefId = bookingRefId;
	}
}
