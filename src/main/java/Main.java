import Flight.GetFlights;
import Joins.CustomersBookings;

public class Main {
    public static void main(String[] args) {
//        CRUDFlight crudFlight = new CRUDFlight();
//        crudFlight.createFlight(123, "Aer Lingus", "BER", "DUB", "2023-11-26 08:00:00", "2023-11-26 10:00:00", 100, 17);
//        crudFlight.selectFlightbyOrigin("BER");
//        crudFlight.selectFlightbyDestination("DXB");
//        crudFlight.updateExistingFlight(123, "Ryanair", "DUB", "BER", "2023-11-26 08:00:00", "2023-11-26 10:00:00", 100.00, 17);
//        crudFlight.displayAllFlights();
//        crudFlight.deleteFlight(123);
//        crudFlight.displayAllFlights();
//
//        CRUDCustomer crudCustomer = new CRUDCustomer();
//        crudCustomer.createCustomer(4, "Michael Jackson", "michael@jackson5.com", 12341234);
//        crudCustomer.selectCustomerByEmail("hannah@hannah.com");
//        crudCustomer.updateExistingCustomer(2, "Santa Claus", "santa@northpole.hohoho", 25122023);
//        crudCustomer.displayAllCustomers();
//        crudCustomer.deleteCustomer(4);
//        crudCustomer.displayAllCustomers();

//        //joins
//        CustomersBookings customersBookings = new CustomersBookings();
//        customersBookings.allCustomersBookings();
//        customersBookings.bookingsByCustomerId(1);
//        customersBookings.bookingsByCustomerEmail("hannah@hannah.com");

        GetFlights getFlights = new GetFlights();
        getFlights.selectFlightbyUserInput();
    }
}