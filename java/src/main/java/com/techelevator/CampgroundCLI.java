package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Campsite;
import com.techelevator.campground.model.CampsiteDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCCampsiteDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.view.Menu;

public class CampgroundCLI {

	private static final String MAIN_MENU_OPTION_VIEW_PARKS = "View Parks";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_VIEW_PARKS,
			MAIN_MENU_OPTION_QUIT };

	// private static final String PARK_CHOICE_OPTION

	private static final String PARK_INFO_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_INFO_MENU_OPTION_SEARCH_RESERVATION = "Search for Reservation";
	private static final String PARK_INFO_MENU_OPTION_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String PARK_INFO_MENU_OPTION_QUIT = "Quit";
	private static final String[] PARK_INFO_MENU_OPTIONS = new String[] { PARK_INFO_MENU_OPTION_VIEW_CAMPGROUNDS,
			PARK_INFO_MENU_OPTION_SEARCH_RESERVATION, PARK_INFO_MENU_OPTION_PREVIOUS_SCREEN,
			PARK_INFO_MENU_OPTION_QUIT };

	private static final String CAMPGROUND_INFO_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String CAMPGROUND_INFO_MENU_OPTION_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String[] CAMPGROUND_INFO_MENU_OPTIONS = new String[] {
			CAMPGROUND_INFO_MENU_OPTION_VIEW_CAMPGROUNDS, CAMPGROUND_INFO_MENU_OPTION_PREVIOUS_SCREEN };

//	private static final String RESERVATION_SEARCH_

	private com.techelevator.campground.view.Menu menu;
	// removed statics
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;
	private long selectedParkId;
	private long campgroundId;
	private LocalDate startDate;
	private LocalDate endDate;
	

	// closed the main
	public static void main(String[] args) {
		
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

	public CampgroundCLI()

	{
		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		campsiteDAO = new JDBCCampsiteDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}

	public void run() {
		displayApplicationBanner();
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_VIEW_PARKS)) {

				handleListAllParks();
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				System.exit(0);
			}

		}
	}

	private void handleListAllParks() {

		printHeading("All Parks");

		printStartHeading("View Parks Interface");
		printHeading("Select a Park for Further Details");

		List<Park> park = parkDAO.getAllParks();
		String[] parkArray = new String[park.size() + 1];
		for (int i = 0; i < parkArray.length - 1; i++) {
			parkArray[i] = park.get(i).getName();

		}
		parkArray[parkArray.length - 1] = "Return to previous screen";
		String name = (String) menu.getChoiceFromOptions(parkArray);
		parkDetailsAndGetNextChoice(name);
//		if (choice.contentEquals(parkArray.length - 1)) {
//			run();
//		}
	}

	private void parkDetailsAndGetNextChoice(String name) {
		Park park = parkDAO.getParkByName(name);
		selectedParkId = park.getParkId();
		System.out.println(park);
		String choice = (String) menu.getChoiceFromOptions(PARK_INFO_MENU_OPTIONS);
		System.out.println(choice);
		if (choice.contentEquals(CAMPGROUND_INFO_MENU_OPTION_VIEW_CAMPGROUNDS)) {

			handleListAllCampgrounds(this.selectedParkId);
			
		}else if (choice.contentEquals(PARK_INFO_MENU_OPTION_SEARCH_RESERVATION)) { 
			//handleReservationSearchById();
		}else if (choice.contentEquals(PARK_INFO_MENU_OPTION_PREVIOUS_SCREEN)) {
			
			handleListAllParks();
			
		}else if (choice.contentEquals(PARK_INFO_MENU_OPTION_QUIT)) {
			System.exit(0); 
			}
		}
			
	private void handleListAllCampgrounds(long selectedParkId) {
		List<Campground> campground = campgroundDAO.getCampgroundsByParkId(selectedParkId);
		String[] campgroundNames = new String[campground.size() + 1];
		System.out.println("Name                         Open From          Open To             Price");
		System.out.println("***************************************************************************");
		for (int i = 0; i < campgroundNames.length - 1; i++) {
			campgroundNames[i] = campground.get(i).toString();
		}
		campgroundNames[campgroundNames.length - 1] = "Return to Previous Screen";
		String name = (String) menu.getChoiceFromOptions(campgroundNames);
//		if (menu.getChoiceFromOptions(campgroundNames).equals(CAMPGROUND_INFO_MENU_OPTION_PREVIOUS_SCREEN)) {
//			return;
//		}
//		System.out.println("Which campground? (enter 0 to cancel): ");

		
		Campground camp = campgroundDAO.getCampgroundByName(name);
		long campgroundId = camp.getCampgroundId();
		
		System.out.println("What is the arrival date?  __/__/____");
		
		String arrivalDate = menu.getUserInput();
		LocalDate startDate = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		System.out.println("What is the departure date? __/__/____");
		
		String departureDate = menu.getUserInput();
		LocalDate endDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
List<Campsite> campsite = campsiteDAO.getAvailSites(campgroundId, startDate, endDate);

//		String[] campsiteNames = new String[campsite.size()];
		System.out.println("Site No.   Max Occup.   Accessible?   Max RV Length   Utility    Cost");
//		for (int i = 0; i < campsite.size(); i++) {
//			campsiteNames[i] = campsite.get(i).toString();
//		}
		if (campsite.size() == 0) {
			System.out.println("No results match your search. Please try again.");
			return;
		}
		
		for (Campsite c: campsite) {
//			String access ="";
//			if (isAccessible) { access = "yes"; } else { access = "no"; }
//			String utility = "";
//					if (hasUtilities) {utility = "yes"; } else {utility = "no";}
			
			System.out.println("    " +c.getSiteNumber() + "          " +
					c.getMaxOccupancy() + "          " +
					c.isAccessible() + "            " +
					c.getMaxRvLength() + "          " +
					c.isHasUtilities() + "      " +
					"$"+c.getDailyFee());
		}
//		if ()
			
//		String customerSiteSelection = (String) menu.getChoiceFromOptions(campsiteNames);
		
		System.out.println("Which site should be reserved (enter 0 to cancel)? __  ");

		int siteNo = Integer.parseInt(menu.getUserInput());
		System.out.println("What name should the reservation be made under? ___");
		String reservationName = menu.getUserInput();
		if (siteNo == 0) {
			return;
		}
		Reservation reservation = new Reservation ();
		reservation.setName(reservationName);
		reservation.setSiteId(siteNo);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setCreateDate(LocalDate.now());
		
		
		
	Reservation reserve = reservationDAO.createNewReservation(reservation);
	System.out.println(reserve);
	//System.out.println(reservationDAO.getReservationByname(reserve.getName()));
		
//		siteNo,startDate, endDate, LocalDate.now());
		// create the reservation -- save to database
		//print out a reservation saved message -- confirmaiton number
}
		
		
		
//	  			if (choice.contentEquals(CAMPGROUND_INFO_MENU_OPTION_PREVIOUS_SCREEN)) {
//	  				parkDetailsAndGetNextChoice(name);}
	  	//	} else if (choice.contentEquals())
	  	//	handleCampSiteReservationSearch();
		
	
	

	//private void handleReservationSearchById() { 
	//prompts user for confirmation number
	//returns reservation if exists}
	
	
	
	//private void handleCampsiteReservationSearch() {
	//prompts user to select campground
	//prompts user for arrival date
	//prompts user for departure date
	//calls handle method to display 5 campsite results}
	
	
	
	//private void handleCampsiteReservation() {
	//prompts user which site to reserve
	//prompts user to enter name for reservation
	//returns reservation_id as confirmation_id
	//returns to main menu }

	
//how does this work
	private void printHeading(String headingText) {
		System.out.println(headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private void printStartHeading(String headingText) {
		System.out.println("\n" + headingText);
	}

	@SuppressWarnings("resource")
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

	private void displayApplicationBanner() {
		System.out.println("********************************************************************");
		System.out.println("*****************National Park Campsite Database********************");
		System.out.println("********************************************************************");

	}
}
