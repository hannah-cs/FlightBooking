package Flight;

import org.example.DBManager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDFlight {

    public void createFlight(int flightId, String airline, String origin, String destination, String departureTime, String arrivalTime, double price, int seatsAvailable) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "INSERT INTO flight (FlightId, Airline, Origin, Destination, DepartureTime, ArrivalTime, Price, SeatsAvailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setInt(1, flightId);
                pst.setString(2, airline);
                pst.setString(3, origin);
                pst.setString(4, destination);
                pst.setString(5, departureTime);
                pst.setString(6, arrivalTime);
                pst.setDouble(7, price);
                pst.setInt(8, seatsAvailable);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight created successfully.");
                } else {
                    System.out.println("Failed to create flight.");
                }
                DatabaseManager.closeConnection(connection);
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
            DatabaseManager.closeConnection(connection);
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
                DatabaseManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void updateExistingFlight(int flightId, String airline, String origin, String destination, String deptTime, String arrTime, double price, int seats) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "UPDATE flight SET Airline = ?, origin = ?, destination = ?, departureTime = ?, arrivalTime = ?, price = ?, SeatsAvailable = ? WHERE flightId = ?;";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, airline);
                pst.setString(2, origin);
                pst.setString(3, destination);
                pst.setString(4, deptTime);
                pst.setString(5, arrTime);
                pst.setDouble(6, price);
                pst.setInt(7, seats);
                pst.setInt(8, flightId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight updated successfully.");
                } else {
                    System.out.println("Failed to update flight.");
                }
                DatabaseManager.closeConnection(connection);
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
                    DatabaseManager.closeConnection(connection);
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
                DatabaseManager.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println("Error connecting to the database");
                e.printStackTrace();
            }
        }
    }