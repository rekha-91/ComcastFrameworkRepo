package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws SQLException 
	{
		//Create Project in GUI
		String ProjectName= "Tek_Pyramid_02";
		
		//WebDriver driver= new ChromeDriver();
		WebDriver driver= new FirefoxDriver();
		driver.get("http://106.51.90.215:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(ProjectName);
		driver.findElement(By.name("createdBy")).sendKeys("r_yantra");
		//WebElement status =driver.findElement(By.name("status"));
		WebElement status =driver.findElement(By.xpath("(//select)[3]"));
		Select sel= new Select(status);
		sel.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();	
		
		//Verify the project in DB
		boolean flag= false;
		
				Driver driverref = new Driver();                  
				DriverManager.registerDriver(driverref);
				
				
				Connection conn= DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		         System.out.println("===Done===");
		         
		         Statement statemnt = conn.createStatement();
		         
		         ResultSet resultset = statemnt.executeQuery("select * from project");
		         while(resultset.next())
		         {
		        	 String actualProjectName= resultset.getString(4);
		        	 if(ProjectName.equals(actualProjectName))
		        	 {
		        		 flag=true;
		        		 System.out.println(ProjectName+" is available in Database");
		        	 }
		         }
		         if(flag==false)
		         {
		        	 System.out.println(ProjectName+ " is not available in Database==fail");
		        	 
		         }
		       
		         conn.close();
		

	}

}
