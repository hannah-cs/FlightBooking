package Flight;

import org.example.DBManager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDFlight {

    public void createFlight(String flightDetails) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "INSERT INTO flight (FlightId, Airline, Origin, Destination, DepartureTime, ArrivalTime, Price, SeatsAvailable) VALUES ("+flightDetails+");";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight created successfully.");
                } else {
                    System.out.println("Failed to create flight.");
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
        System.out.println("Error connecting to the database");
        e.printStackTrace();
    }
    }

    public void selectFlightbyOrigin(String origin){
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully. Flights with origin "+origin+":");
            String query = "SELECT * FROM flight WHERE origin LIKE '"+origin+"';";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void selectFlightbyDestination(String destination){
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully. Flights with destination "+destination+":");
            String query = "SELECT * FROM flight WHERE destination LIKE '"+destination+"';";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void updateExistingFlight (int flightId, String airline, String origin, String destination, String deptTime, String arrTime, Double price, int seats){
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "UPDATE flight SET Airline = '"+airline+"', origin = '"+origin+"', destination = '"+destination+"', departureTime = '"+deptTime+"', arrivalTime = '"+arrTime+"', price = "+price+", SeatsAvailable = "+seats+" WHERE flightId = "+flightId+";";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight updated successfully.");
                } else {
                    System.out.println("Failed to create flight.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
        public void displayAllFlights () {
            try (Connection connection = DatabaseManager.getConnection()) {
                System.out.println("Connected to the database successfully. All flights:");

                String query = "SELECT * FROM flight;";
                try (PreparedStatement pst = connection.prepareStatement(query);
                     ResultSet rs = pst.executeQuery()) {
                    System.out.println();
                    while (rs.next()) {
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

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Error connecting to the database");
                e.printStackTrace();
            }
        }

        public void deleteFlight(int flightId){
            try (Connection connection = DatabaseManager.getConnection()) {
                System.out.println("Connected to the database successfully.");
                String query = "DELETE FROM flight WHERE flightId = "+flightId;
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Flight deleted successfully.");
                    } else {
                        System.out.println("Failed to delete flight.");
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