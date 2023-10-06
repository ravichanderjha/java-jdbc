package org.rcjha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/jdbc-example-01";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            /*
            // Execute SQL query
            String sql = "SELECT * FROM your_table_name";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
             */
            if(null != connection)
                System.out.println("Connection Has established!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }}
