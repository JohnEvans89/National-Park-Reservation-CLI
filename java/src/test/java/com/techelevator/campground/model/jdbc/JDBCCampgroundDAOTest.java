package com.techelevator.campground.model.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;



public class JDBCCampgroundDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCCampgroundDAO dao;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test 
	public void get_campgrounds_by_park_id_enter_park_id_6_return_2_campgrounds() {
	
		long testParkId = 6;
		
		long testIdCampground1 = 15;
		long testIdCampground2 = 16;
		
		List<Campground> park6Campgrounds = dao.getCampgroundsByParkId(testParkId);
		
		int testCampgroundCounter = 0;
		
		for(Campground campground: park6Campgrounds) {
			
			if(campground.getCampgroundId() == testIdCampground1 || campground.getCampgroundId() == testIdCampground2) {
				testCampgroundCounter++;
			}
			
		}
		
		assertNotEquals(null,park6Campgrounds);
		assertEquals(2, testCampgroundCounter);
		
	}
	public void createCampgrounds() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String insertStatement = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) ";
	
		String campground1 = "VALUES (15, 6, 'Bird's Nest', '06-01-2020','08-30-2020', $35.00')";		
		String campground2 = "VALUES (16, 6, 'Cabin Fever', '06-01-2020','08-30-2020', $25.00')";
		
		List<String> campgroundsValue = new ArrayList<>();
		campgroundsValue.add(campground1);
		campgroundsValue.add(campground2);
		
		
		for (int i = 0; i < campgroundsValue.size(); i++) {
			String sqlNewCampground = "";
			sqlNewCampground = insertStatement + campgroundsValue.get(i);	
			jdbcTemplate.update(sqlNewCampground);
			}
	}
	private void assertCampgroundsAreEqual(Campground expected, Campground actual) {
		
		assertEquals(expected.getParkId(), actual.getParkId());
		assertEquals(expected.getName(), actual.getName());	
		assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
		assertEquals(expected.getOpenFromMonth(), actual.getOpenFromMonth());
		assertEquals(expected.getOpenToMonth(), actual.getOpenToMonth());
		assertEquals(expected.getDailyFee(), actual.getDailyFee());

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


