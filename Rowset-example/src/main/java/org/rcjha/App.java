package org.rcjha;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    static String dbUrl = "jdbc:postgresql://localhost:5432/jdbc-example-01";
    static String user = "postgres";
    static String password = "pass";
    public static void main( String[] args ) throws SQLException {
        JdbcRowSet jdbcRowSet = getJdbcRowSet();

        String query = "select * from employee";
        jdbcRowSet.setCommand(query);
        jdbcRowSet.execute();

        while(jdbcRowSet.next()){
            System.out.println("ID: " + jdbcRowSet.getInt("id"));
            System.out.println("Name: " + jdbcRowSet.getString("name"));
            System.out.println("Salary: " + jdbcRowSet.getDouble("salary"));
            System.out.println("---------------------");
        }

        //Prepared Statement
        query = "select * from employee where id = ?";
        jdbcRowSet.setCommand(query);
        jdbcRowSet.setInt(1, 2);
        jdbcRowSet.execute();
        while(jdbcRowSet.next()){
            System.out.println("ID: " + jdbcRowSet.getInt("id"));
            System.out.println("Name: " + jdbcRowSet.getString("name"));
            System.out.println("Salary: " + jdbcRowSet.getDouble("salary"));
            System.out.println("---------------------");
        }
    }
    static JdbcRowSet getJdbcRowSet(){
        JdbcRowSet jdbcRowSet = null;
        try{
            jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRowSet.setUrl(dbUrl);
            jdbcRowSet.setUsername(user);
            jdbcRowSet.setPassword(password);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return jdbcRowSet;
    }
}
