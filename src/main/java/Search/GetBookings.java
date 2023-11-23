package Search;

import org.example.DBManager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetBookings {
    public void selectBookingbyUserInput() {
        try (Connection connection = DatabaseManager.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Search bookings. Enter search term:");
            String searchTerm = scanner.nextLine();

            String query = "SELECT * FROM booking WHERE bookingId LIKE ? OR customerId LIKE ? OR flightId LIKE ?;";

            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, searchTerm);
                pst.setString(2, searchTerm);
                pst.setString(3,  searchTerm );
                boolean foundresults = false;
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        foundresults = true;
                        System.out.println("Booking ID: " + rs.getInt("BookingId"));
                        System.out.println("Booking date: " + rs.getDate("BookingDate"));
                        System.out.println("Number of passengers: " + rs.getInt("NumberPassengers"));
                        System.out.println("Booking status: " + rs.getString("Status"));
                        System.out.println("Customer ID:" + rs.getInt("CustomerId"));
                        System.out.println();
                    }
                    if (!foundresults){
                        System.out.println("No results found for "+searchTerm);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
