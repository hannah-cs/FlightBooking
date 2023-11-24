import Search.SearchMenu;
import Booking.FlightBooker;

public class Main {
    public static void main(String[] args) {
//        SearchMenu searchMenu = new SearchMenu();
//        searchMenu.searchDB();
        FlightBooker flightBooker = new FlightBooker();
        flightBooker.bookFlight(3, 1, 4);
        flightBooker.bookFlight(1, 2, 55);
    }
}