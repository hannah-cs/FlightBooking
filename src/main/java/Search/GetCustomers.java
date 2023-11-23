package Search;

import org.example.DBManager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetCustomers {
        public void selectCustomerbyUserInput() {
            try (Connection connection = DatabaseManager.getConnection();
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Search customers. Enter search term:");
                String searchTerm = scanner.nextLine();

                String query = "SELECT * FROM customer WHERE customerId LIKE ? OR name LIKE ? OR email LIKE ? OR phone LIKE ?;";

                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.setString(1, "%" + searchTerm + "%");
                    pst.setString(2, "%" + searchTerm + "%");
                    pst.setString(3, "%" + searchTerm + "%");
                    pst.setString(4,  searchTerm );
                    boolean foundresults = false;
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            foundresults = true;
                            System.out.println("Name: " + rs.getString("name"));
                            System.out.println("Customer ID: " + rs.getInt("customerId"));
                            System.out.println("Email: " + rs.getString("email"));
                            System.out.println("Phone: " + rs.getInt("Phone"));
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