package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getCampgroundsByParkId(long park_id) {
		ArrayList<Campground> campgrounds = new ArrayList<>();
		String sqlGetCampgrounds = "Select * FROM campground WHERE park_id = ?";
//		String queryGetCampgroundNameByParkId = "Select * From campground Where park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgrounds, park_id);
		while (results.next()) {
			Campground theCampground = mapRowToCampground(results);
			campgrounds.add(theCampground);
		}
		return campgrounds;
	}
	@Override
	public Campground getCampgroundByName(String name) {
		Campground theCampground = new Campground(); 
		String sqlGetCampgroundByName  = "Select * FROM campground WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundByName, name);
		if(results.next()) {
			theCampground = mapRowToCampground(results);
			
		}
		return theCampground;
	}

	private Campground mapRowToCampground(SqlRowSet results) {
		Campground theCampground = new Campground();
		theCampground.setCampgroundId(results.getLong("campground_id"));
		theCampground.setParkId(results.getLong("park_id"));
		theCampground.setName(results.getString("name"));
		theCampground.setOpenFromMonth(results.getInt("open_from_mm"));
		theCampground.setOpenToMonth(results.getInt("open_to_mm"));
		theCampground.setDailyFee(results.getBigDecimal("daily_fee"));

		return theCampground;
	}


}
