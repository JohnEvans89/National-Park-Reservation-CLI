package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campsite;
import com.techelevator.campground.model.CampsiteDAO;

public class JDBCCampsiteDAO implements CampsiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampsiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Campsite> getAvailSites(Long campgroundId, LocalDate startDate, LocalDate endDate){
		List<Campsite> campsiteList = new ArrayList<>();
		campgroundId++;  // added one to map to same on the database side
		String querySearchCampsiteById = "SELECT * " +
		"FROM site s JOIN campground c ON s.campground_id = c.campground_id WHERE s.campground_id = ? "+
				"AND s.site_id NOT IN (SELECT s.site_id FROM reservation r WHERE (?,?) OVERLAPS (from_date, to_date) " +
		"GROUP BY s.site_id) LIMIT 5";
		SqlRowSet results= jdbcTemplate.queryForRowSet(querySearchCampsiteById, campgroundId, startDate, endDate);
		
		while (results.next()){
			Campsite c = mapRowToCampsite(results);		
			campsiteList.add(c);
		}
		return campsiteList;
		
	
	
//	@Override
//	public Campsite getCampsiteByCampgroundId(long campgroundId) {
//		Campsite camp = null;
//		String sqlgetCampsiteByCampgroundId = "SELECT site_number, max_occupancy, accessible,max_rv_length, utilities FROM site WHERE campground_id = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetCampsiteByCampgroundId, campgroundId);
//
//		if (results.next()) {
//			camp = mapRowToCampsite(results);
//		}
//		return camp;
//	}

//	@Override
//	public List<Campsite> getCampsitesByCampgroundId(long campgroundId) {
//		ArrayList<Campsite> campsites = new ArrayList<>();
//		String sqlGetCampsites = "SELECT * FROM campsite WHERE campground_id = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampsites, campgroundId);
//
//		while (results.next()) {
//			Campsite theCampsite = mapRowToCampsite(results);
//			campsites.add(theCampsite);
//		}
//		return campsites;
//	}

}


	
	private Campsite mapRowToCampsite(SqlRowSet results) {
		Campsite campsite = new Campsite();
		campsite.setSiteId(results.getLong("site_id"));
		campsite.setCampgroundId(results.getLong("campground_id"));
		campsite.setSiteNumber(results.getInt("site_number"));
		campsite.setMaxOccupancy(results.getInt("max_occupancy"));
		campsite.setIsAccessible(results.getBoolean("accessible"));
		campsite.setMaxRvLength(results.getInt("max_rv_length"));
		campsite.setHasUtilities(results.getBoolean("utilities"));
		campsite.setDailyFee(results.getBigDecimal("daily_fee"));
		return campsite;
	}

	

}
