/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataIO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author shofa
 */
public class DBUtil {
    String dbURL = "jdbc:mysql://localhost:3308/music2024?useSSL=false&serverTimezone=UTC";
    String username = "root";
    String password = "1234";
    String drivername="com.mysql.cj.jdbc.Driver";
    
    Connection connection=null;
    Statement statement=null;
    
    public DBUtil(){
          try{
            Class.forName(drivername);
            connection = DriverManager.getConnection(dbURL, username, password);
          }
        catch(Exception e)
        {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
    
    public ResultSet QueryData(String sql)
    {
       try{
               statement = connection.createStatement();
               ResultSet productResult = statement.executeQuery( sql);
               return productResult;
          }
        catch(Exception e)
        {
            System.err.println("❌ QueryData Error: " + e.getMessage());
            return null;
        }
    }
    
     public int QueryUpdate(String sql)
    {
       try{
               statement = connection.createStatement();
               int num = statement.executeUpdate(sql);
               return num;
          }
        catch(Exception e)
        {
            System.err.println("QueryData Error: " + e.getMessage());
            return 0;
        }
    }
    
    public boolean Close()
    {
        try{
              if (connection != null) connection.close();
              return true;
          }
        catch(Exception e)
        {
            System.err.println("Error closing DB connection: " + e.getMessage());
            return false;
        }
    }
}
