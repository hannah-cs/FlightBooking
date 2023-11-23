package Joins;
import org.example.DBManager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersBookings {
    public void allCustomersBookings() {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "SELECT c.CustomerId, c.name, b.BookingId, b.FlightId, b.Status, b.NumberPassengers FROM customer c LEFT JOIN booking b ON c.CustomerId=b.CustomerId;";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("CustomerId"));
                    System.out.println("Customer Name: " + rs.getString("name"));
                    System.out.println("Booking ID: " + rs.getInt("BookingId"));
                    System.out.println("Number of passengers: " + rs.getInt("NumberPassengers"));
                    System.out.println("Booking status: " + rs.getString("Status"));
                    System.out.println("Flight ID:" + rs.getInt("FlightId"));
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void bookingsByCustomerId(int CID) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "SELECT c.CustomerId, c.name, b.BookingId, b.FlightId, b.Status, b.NumberPassengers FROM customer c LEFT JOIN booking b ON c.CustomerId=b.CustomerId WHERE c.customerId = "+CID;
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("CustomerId"));
                    System.out.println("Customer Name: " + rs.getString("name"));
                    System.out.println("Booking ID: " + rs.getInt("BookingId"));
                    System.out.println("Number of passengers: " + rs.getInt("NumberPassengers"));
                    System.out.println("Booking status: " + rs.getString("Status"));
                    System.out.println("Flight ID:" + rs.getInt("FlightId"));
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
    public void bookingsByCustomerEmail(String cemail) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "SELECT c.CustomerId, c.name, b.BookingId, b.FlightId, b.Status, b.NumberPassengers FROM customer c LEFT JOIN booking b ON c.CustomerId=b.CustomerId WHERE c.email = '"+cemail+"';";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("CustomerId"));
                    System.out.println("Customer Name: " + rs.getString("name"));
                    System.out.println("Booking ID: " + rs.getInt("BookingId"));
                    System.out.println("Number of passengers: " + rs.getInt("NumberPassengers"));
                    System.out.println("Booking status: " + rs.getString("Status"));
                    System.out.println("Flight ID:" + rs.getInt("FlightId"));
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
