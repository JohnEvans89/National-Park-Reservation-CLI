package com.techelevator.campground.model;

import java.util.List;

public interface ParkDAO {

	public List<Park> getAllParks();

//	public Park getParkById(long id);

	public Park getParkByName(String name);

}
