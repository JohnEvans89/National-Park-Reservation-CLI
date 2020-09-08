package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface CampsiteDAO {

	
//	public List<Campsite> getAllCampsites();
	
//	public Campsite getCampsiteByCampgroundId(long campgroundId);
	
//	public List<Campsite> getCampsitesByCampgroundId(long campgroundId);
	public List<Campsite> getAvailSites(Long campgroundId, LocalDate startDate, LocalDate endDate);
	
}
