package Search;

import org.example.DBManager.DatabaseManager;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetFlights {
    public void selectFlightbyUserInput() {
        try (Connection connection = DatabaseManager.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Search flights. Enter search term:");
            String searchTerm = scanner.nextLine();

            String query = "SELECT * FROM flight WHERE flightId LIKE ? OR airline LIKE ? OR origin LIKE ? OR destination LIKE ?;";

            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, "%" + searchTerm + "%");
                pst.setString(2, "%" + searchTerm + "%");
                pst.setString(3, "%" + searchTerm + "%");
                pst.setString(4, "%" + searchTerm + "%");
                boolean foundResults = false;
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        foundResults = true;
                        System.out.println("Flight ID: " + rs.getInt("FlightId"));
                        System.out.println("Airline: " + rs.getString("Airline"));
                        System.out.println("Origin: " + rs.getString("Origin"));
                        System.out.println("Destination: " + rs.getString("Destination"));
                        System.out.println("Departing at: " + rs.getDate("DepartureTime"));
                        System.out.println("Arriving at: " + rs.getDate("ArrivalTime"));
                        System.out.println("Price: €" + rs.getString("Price"));
                        System.out.println("Number of seats available:" + rs.getInt("SeatsAvailable"));
                        System.out.println();
                    }
                    if (!foundResults) {
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