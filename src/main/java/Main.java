import Search.GetBookings;
import Search.GetCustomers;
import Search.GetFlights;
import Search.SearchMenu;

public class Main {
    public static void main(String[] args) {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.searchDB();
    }
}