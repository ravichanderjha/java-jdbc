package org.rcjha;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost"); // Replace with your PostgreSQL server name
        dataSource.setDatabaseName("jdbc-example-01"); // Replace with your database name
        dataSource.setUser("postgres"); // Replace with your username
        dataSource.setPassword("pass"); // Replace with your password

        Connection connection = null;
        try {
            // Get a connection from the DataSource
            connection = dataSource.getConnection();
            if(null != connection)
                System.out.println("Connection Has established!");
            Statement statement = connection.createStatement();
            String query = "select * from employee";

            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("---------------------");
            }

            //Prepared Statement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where id = ?");

            preparedStatement.setInt(1,2);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("---------------------");
            }

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
    }
}
