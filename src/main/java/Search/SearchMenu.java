package Search;

import java.util.Scanner;

public class SearchMenu {
    public void searchDB() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome. Which table would you like to search?");
        System.out.println("1. Flights");
        System.out.println("2. Customers");
        System.out.println("3. Bookings");
        String choice = scanner.nextLine();
        if (choice.contains("1")){
            GetFlights getFlights = new GetFlights();
            getFlights.selectFlightbyUserInput();
        } else if (choice.contains("2")){
            GetCustomers getCustomers = new GetCustomers();
            getCustomers.selectCustomerbyUserInput();
        } else if (choice.contains("3")){
            GetBookings getBookings = new GetBookings();
            getBookings.selectBookingbyUserInput();
        } else {
            System.out.println("Invalid input");
        }
    }
}
