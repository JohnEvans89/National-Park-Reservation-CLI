package com.techelevator.campground.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Park {

	private long parkId;
	private String name;
	private String location;
	private LocalDate establishedDate;
	private int area;
	private int visitors;
	private String description;
	
	

//	public Park(String name, String location, LocalDate establishedDate, int area, int visitors, String description) {
//		super();
//		this.name = name;
//		this.location = location;
//		this.establishedDate = establishedDate;
//		this.area = area;
//		this.visitors = visitors;
//		this.description = description;
//	}
	
	public long getParkId() {
		return parkId;
	}
	public void setParkId(long parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
		public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return name + " " + "National Park" +
		"\n" + "Location:         " + location + 
	    "\n" + "Established:      " + establishedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + 
	    "\n" + "Area:             " + NumberFormat.getIntegerInstance().format(area) + " sq km"+ 
	    "\n" + "Annual Visitors:  " + NumberFormat.getIntegerInstance().format(visitors) +
	    "\n" +
		"\n" + description;
		
	}

	
}
