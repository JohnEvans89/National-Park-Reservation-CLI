package com.techelevator.campground.model.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;

public class JDBCParkDAOTest {

	
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO parkDAO;
	
//	private static final Long testParkId = (long) 20;
//	private static final String testParkName = "YellowStone";

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

//I don't understand what I need to do to get these tests working.
//I'd like to spend some time outside of class trying to fix these -Brandon
	@Test
	public void get_all_parks() {
		List<Park> results = parkDAO.getAllParks();
		assertNotNull(results);
		assertEquals(3, results.size());
	}
	
//	@Test
//	public void get_park_by_name() {
//		Park park = getPark( testParkId, testParkName);
//		parkDAO.getParkByName(park);
//		
//		List<Park> results = parkDAO.getParkByName(testParkId, testParkName);
//		
//		Park result = results.get(0);
//		
//		assertNotNull(results);
//		assertEquals (1, results.size());
//		assertParksAreEqual (result, park);
//	}
	
	private void assertParksAreEqual (Park expected, Park actual) {
		assertEquals(expected.getParkId(), actual.getParkId());
		assertEquals(expected.getName(), actual.getName());
	}
	
//	private Park mapRowToPark(SqlRowSet results) {
//		Park thePark = new Park();
//		thePark.setParkId(results.getLong("park_id"));
//		thePark.setName(results.getString("name"));
//		thePark.setLocation(results.getString("location"));
//		thePark.setEstablishedDate(results.getDate("establish_date").toLocalDate());
//		thePark.setArea(results.getInt("area"));
//		thePark.setVisitors(results.getInt("visitors"));
//		thePark.setDescription(results.getString("description"));
//		return thePark;
//	}

}
