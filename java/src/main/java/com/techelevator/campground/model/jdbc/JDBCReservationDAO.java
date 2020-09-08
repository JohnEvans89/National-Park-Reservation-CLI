package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;


public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public Reservation createNewReservation(Reservation newReservation) {
			String sqlNewReservation = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) VALUES (?,?,?,?,?)";
			
//			newReservation.setReservationId(getNextReservationId());
			
			jdbcTemplate.update(sqlNewReservation, newReservation.getSiteId(), newReservation.getName(), newReservation.getStartDate(), newReservation.getEndDate(), newReservation.getCreateDate());
			
			return newReservation;
		}
	

	private long getNextReservationId() {
	
			SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_reservation_id')");
			if(nextIdResult.next()) {
				return nextIdResult.getLong(1);
			} else {
				throw new RuntimeException();
			}

	}

	
	
	
	
	
//@Override
//	public List<Reservation> getAllReservations(long reservationId) {
//		ArrayList<Reservation> reservation = new ArrayList<>();
//		String sqlGetAllReservations = "SELECT * FROM reservation WHERE reservation_id = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllReservations, reservationId);
//
//		while (results.next()) {
//			Reservation theReservation = mapRowToReservation(results);
//			reservation.add(theReservation);
//		}
//		return reservation;
//	}

//one return
//	@Override
//	public Reservation getReservationByReservationId(Long id) {
//		Reservation reservationName = null;
//		String sqlgetReservationByReservationId = "SELECT reservation_id, name FROM reservation WHERE reservation_id = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetReservationByReservationId, id);
//
//		if (results.next()) {
//			reservationName = mapRowToReservation(results);
//		}
//		return reservationName;}
		
		//one return
//		@Override
//		public Reservation getReservationByname(String name) {
//			Reservation reservationName = null;
//			String sqlgetReservationByReservationId = "SELECT reservation_id, name FROM reservation WHERE name = ?";
//			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetReservationByReservationId, name);
//
//			if (results.next()) {
//				reservationName = mapRowToReservation(results);
//			}
//			return reservationName;
//	}
	
	
	
//	@Override
//	public Reservation getAllByReservationId(Long reservationId) {
//		Reservation retrieveReservation = null;
//		String sqlgetReservationByReservationId = "SELECT * FROM reservation WHERE reservation_id = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetReservationByReservationId, reservationId);
//
//		if (results.next()) {
//			retrieveReservation = mapRowToReservation(results);
//		}
//		return retrieveReservation;
//	}

	// one return
//	@Override
//	public List<Reservation> getAllByName(String name) {
//		ArrayList<Reservation> reserve = new ArrayList<>();
//		String sqlgetAllByName = "SELECT * FROM reservation WHERE name = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetAllByName, name);
//
//		// list of returns
//		while (results.next()) {
//			Reservation reservation = mapRowToReservation(results);
//			reserve.add(reservation);
//		}
//
//		return reserve;
//	}

	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation theReserve;
		theReserve = new Reservation();
		theReserve.setReservationId(results.getLong("reservation_id"));
		theReserve.setSiteId(results.getLong("site_id"));
		theReserve.setName(results.getString("name"));
		theReserve.setStartDate(results.getDate("from_date").toLocalDate());
		theReserve.setEndDate(results.getDate("to_date").toLocalDate());
		theReserve.setCreateDate(results.getDate("create_date").toLocalDate());
		return theReserve;
	}
}




