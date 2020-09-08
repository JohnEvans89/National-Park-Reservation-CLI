package com.techelevator.campground.model;

import java.time.LocalDate;

public class Reservation {

	private long reservationId;
	private long siteId;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate createDate;
	private long confirmationId = reservationId;
	
	
	
	
	public long getReservationId() {
		return reservationId;
		
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "The reservation has been made and the confirmation id is " 
	+  confirmationId;
	/*+ "Name on reservation:" + name
	+ "Site Id Number:"  + siteId 
	+ "Dates" + startDate + "-" 
	+ endDate 
	+ "Date Reservation was created:" 
	+ createDate 
	;*/

	}
}
