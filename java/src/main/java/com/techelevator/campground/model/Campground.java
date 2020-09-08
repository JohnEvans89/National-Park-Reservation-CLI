package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Campground {
	
	private long campgroundId;
	private long parkId;
	private String name;
	private int openFromMonth;
	private int openToMonth;
	private BigDecimal dailyFee;
	
	
	
	


	//System.out.printf("%-4s%-20s$ %-8s%-2s%-20s%n",name,getMonth(openFromMonth) ,getMonth(openToMonth),"$"+dailyFee);
	
	@Override
	public String toString() {		
		//return String.format("%d %d %d", a, b, c);
		return  String.format("%-25s%-20s%-20s$%-20s",name,getMonth(openFromMonth),getMonth(openToMonth),dailyFee.setScale(2));
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}

	/*
	 * @Override public String toString() { return name + " " + "National Park" +
	 * "\n" + "Location:         " + location + "\n" + "Established:      " +
	 * establishedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
	 * + "\n" + "Area:             " +
	 * NumberFormat.getIntegerInstance().format(area) + " sq km"+ "\n" +
	 * "Annual Visitors:  " + NumberFormat.getIntegerInstance().format(visitors) +
	 * "\n" + "\n" + description;
	 * 
	 * }
	 */
	
//	public Campground(long parkId, String name, int openFromMonth, int openToMonth, BigDecimal dailyFee) {
//		super();
//		this.parkId = parkId;
//		this.name = name;
//		this.openFromMonth = openFromMonth;
//		this.openToMonth = openToMonth;
//		this.dailyFee = dailyFee;
//	}
	public long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}
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
	public int getOpenFromMonth() {
		return openFromMonth;
	}
	public void setOpenFromMonth(int openFromMonth) {
		this.openFromMonth = openFromMonth;
	}
	public int getOpenToMonth() {
		return openToMonth;
	}
	public void setOpenToMonth(int openToMonth) {
		this.openToMonth = openToMonth;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	
	
	
}
