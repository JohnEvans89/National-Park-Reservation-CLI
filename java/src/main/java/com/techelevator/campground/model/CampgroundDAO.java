package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {

public List<Campground> getCampgroundsByParkId(long park_id);

public Campground getCampgroundByName(String name);
	

}
