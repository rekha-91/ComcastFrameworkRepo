package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SampleTestDB {
 
	//program to execute select query
	public static void main(String[] args) throws Throwable
	{
		//step1: load/ register the database driver
		Driver driverref = new Driver();                  //driver only we are using from mysqldatabase package driver to load and remaining we are using from sql package.
		DriverManager.registerDriver(driverref);
		
		//step2: connect to database
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
         System.out.println("===Done===");
         
        //step3: create sql statement
         Statement statemnt = conn.createStatement();
         
        //step4: execute select query & get result
         ResultSet resultset = statemnt.executeQuery("select * from projects");
         while(resultset.next())
         {
        	 System.out.println(resultset.getString(1)+"\t"+ resultset.getString(2)+"\t"+ resultset.getInt(3)+"\t"+ resultset.getString(4) );
         }
         
        //step5: close the connection
         conn.close();
	}

}
