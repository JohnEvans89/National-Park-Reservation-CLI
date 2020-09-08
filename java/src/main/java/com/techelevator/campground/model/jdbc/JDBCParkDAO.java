package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campsite;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;



public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;
	


	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		String sqlGetAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while(results.next()) {
			Park thePark = mapRowToPark(results);
			parks.add(thePark);
//			System.out.println(thePark.getName());
		}
		return parks;
	}
	@Override
	public Park getParkByName(String name) {
		Park park = null;
		String queryGetParkByName = "Select * From park Where name = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(queryGetParkByName, name);
		if (results.next()) {
			park = mapRowToPark(results);
		}
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park thePark = new Park();
		thePark.setParkId(results.getLong("park_id"));
		thePark.setName(results.getString("name"));
		thePark.setLocation(results.getString("location"));
		thePark.setEstablishedDate(results.getDate("establish_date").toLocalDate());
		thePark.setArea(results.getInt("area"));
		thePark.setVisitors(results.getInt("visitors"));
		thePark.setDescription(results.getString("description"));
		return thePark;
	}
}
