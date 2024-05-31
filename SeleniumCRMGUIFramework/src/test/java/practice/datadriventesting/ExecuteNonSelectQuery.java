package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQuery 
{
	public static void main(String[] args) throws Throwable {
		//step1: load/ register the database driver
		Connection conn= null;
		try {
				Driver driverref = new Driver();                 
				DriverManager.registerDriver(driverref);
				
				//step2: connect to database
				conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		         System.out.println("===Done===");
		         
		        //step3: create sql statement
		         Statement statemnt = conn.createStatement();
		         
		        //step4: execute select query & get result
		          int result = statemnt.executeUpdate("insert into projects values('Radha','M',32,'FeMale');");
		          System.out.println(result);
		}
		catch(Exception e)
		{
			System.out.println("Handle Exception");
		}
		finally {
		        //step5: close the connection
		         conn.close();
		         System.out.println("====Close the Connection====");
		} 
	}

}
