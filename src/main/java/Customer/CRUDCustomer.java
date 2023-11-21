package Customer;

import org.example.DBManager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDCustomer {

    public void createCustomer(int customerId, String name, String email, long phone) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "INSERT INTO customer (CustomerId, Name, Email, Phone) VALUES (?, ?, ?, ?);";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setInt(1, customerId);
                pst.setString(2, name);
                pst.setString(3, email);
                pst.setLong(4, phone);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer created successfully.");
                } else {
                    System.out.println("Failed to create customer.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void selectCustomerByEmail(String email) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully. Customer with email " + email + ":");
            String query = "SELECT * FROM customer WHERE email LIKE ?;";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, email);
                try (ResultSet rs = pst.executeQuery()) {
                    System.out.println();
                    while (rs.next()) {
                        System.out.println("Customer ID: " + rs.getInt("CustomerId"));
                        System.out.println("Name: " + rs.getString("Name"));
                        System.out.println("Email: " + rs.getString("Email"));
                        System.out.println("Phone: " + rs.getString("Phone"));
                        System.out.println();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void updateExistingCustomer(int customerId, String name, String email, long phone) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "UPDATE customer SET Name = ?, Email = ?, Phone = ? WHERE CustomerId = ?;";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setLong(3, phone);
                pst.setInt(4, customerId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer updated successfully.");
                } else {
                    System.out.println("Failed to update customer.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public void displayAllCustomers() {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully. All customers:");

            String query = "SELECT * FROM customer;";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                System.out.println();
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("CustomerId"));
                    System.out.println("Name: " + rs.getString("Name"));
                    System.out.println("Email: " + rs.getString("Email"));
                    System.out.println("Phone: " + rs.getString("Phone"));
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

    public void deleteCustomer(int customerId) {
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Connected to the database successfully.");
            String query = "DELETE FROM customer WHERE CustomerId = ?;";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setInt(1, customerId);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("Failed to delete customer.");
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
