package com.techelevator.campground.model;

import java.math.BigDecimal;

public class Campsite {

	private long siteId;
	private long campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private int maxRvLength;
	private boolean hasUtilities;
	private boolean isAccessible;
	private BigDecimal dailyFee;
	
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
//	public Campsite(long campgroundId, int siteNumber, int maxOccupancy, int maxRvLength, boolean hasUtilities,
//			boolean isAccessible) {
//		super();
//		this.campgroundId = campgroundId;
//		this.siteNumber = siteNumber;
//		this.maxOccupancy = maxOccupancy;
//		this.maxRvLength = maxRvLength;
//		this.hasUtilities = hasUtilities;
//		this.isAccessible = isAccessible;
//	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public int getMaxRvLength() {
		return maxRvLength;
	}
	public void setMaxRvLength(int maxRvLength) {
		this.maxRvLength = maxRvLength;
	}
	public boolean isHasUtilities() {
		return hasUtilities;
	}
	public void setHasUtilities(boolean hasUtilities) {
		this.hasUtilities = hasUtilities;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public void setIsAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	
	@Override
	public String toString() {		
		String access ="";
		if (isAccessible) { access = "yes"; } else { access = "no"; }
		String utility = "";
				if (hasUtilities) {utility = "yes"; } else {utility = "no";}
		return  ""+ siteId + maxOccupancy + access + maxRvLength + utility + String.format("$%.2f", dailyFee);
	}

	public BigDecimal getDailyFee() {
		return dailyFee;
	}	
}
