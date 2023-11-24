package Booking;
import org.example.DBManager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightBooker {
    public void bookFlight(int flightId, int customerId, int numPassengers) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());

            String bookingQuery = "INSERT INTO booking (BookingDate, NumberPassengers, Status, CustomerId, FlightId) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement bookingPst = connection.prepareStatement(bookingQuery)) {
                bookingPst.setTimestamp(1, Timestamp.valueOf(currentDateTime));
                bookingPst.setInt(2, numPassengers);
                bookingPst.setString(3, "Confirmed");
                bookingPst.setInt(4, customerId);
                bookingPst.setInt(5, flightId);

                bookingPst.executeUpdate();
            }

            String updateQuery = "UPDATE flight SET SeatsAvailable = SeatsAvailable - ? WHERE FlightId = ? AND SeatsAvailable >= ?;";
            try (PreparedStatement updatePst = connection.prepareStatement(updateQuery)) {
                updatePst.setInt(1, numPassengers);
                updatePst.setInt(2, flightId);
                updatePst.setInt(3, numPassengers);

                int rowsAffected = updatePst.executeUpdate();

                if (rowsAffected == 0) {
                    connection.rollback();
                    System.out.println("Booking request for flight "+flightId+" unsuccessful. Not enough seats available.");
                    return;
                }
            }

            connection.commit();
            System.out.println("Successfully booked "+numPassengers+" seats on flight "+flightId+".");

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            System.out.println("Error booking flight.");
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }
}