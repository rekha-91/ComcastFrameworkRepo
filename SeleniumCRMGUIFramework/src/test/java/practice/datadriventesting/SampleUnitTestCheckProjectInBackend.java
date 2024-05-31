package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackend 
{
	@Test
	public void projectCheckTest() throws Throwable
	{
		String expectedFirstName= "John";
		boolean flag= false;
		//step1: load/ register the database driver
				Driver driverref = new Driver();                  
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
		        	 String actualFirstName= resultset.getString(1);
		        	 if(expectedFirstName.equals(actualFirstName))
		        	 {
		        		 flag=true;
		        		 System.out.println(expectedFirstName+" is available in Projects");
		        	 }
		         }
		         if(flag==false)
		         {
		        	 System.out.println(expectedFirstName+ " is not available in Projects==fail");
		        	 Assert.fail();
		         }
		         
		        //step5: close the connection
		         conn.close();
		
	}
}
