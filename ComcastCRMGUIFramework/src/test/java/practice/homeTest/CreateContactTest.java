package practice.homeTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest
{
	public static void main(String[] args) throws Throwable 
	{
		//Create Object
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		//reading data from property file
		
		 String BROWSER= flib.getDataFromPropertiesFile("browser");
		 String URL= flib.getDataFromPropertiesFile("url");
		 String USERNAME= flib.getDataFromPropertiesFile("username");
		 String PASSWORD= flib.getDataFromPropertiesFile("password");
		
		 //reading data from workbook
		 String LastName = elib.getDataFromExcel("Contact", 4, 2) + jlib.getRandomNumber();
		 
		 WebDriver driver= null;
		 
		 if(BROWSER.equals("chrome"))
		 {
			 driver = new ChromeDriver();
		 }
		 else if(BROWSER.equals("firefox"))
		 {
			 driver =new FirefoxDriver();
		 }
		 else if(BROWSER.equals("edge"))
		 {
			 driver = new EdgeDriver();
		 }
		 else 
		 {
			 driver= new ChromeDriver();
		 }
		 // step1: Login
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.get(URL);
		 
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 
		 //step2: navigate to Organization module
		 driver.findElement(By.linkText("Contacts")).click();
		 
		 //step3: click on Create Contact button
		 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		 
		 //step4: Enter all the details & create new contacts
			
		 driver.findElement(By.name("lastname")).sendKeys(LastName);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		
		 //verify the Start Date info expected result
		 String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actualLastName.equals(LastName))
			{
				System.out.println(LastName+" is verified==pass");
			}
			else
			{
				System.out.println(LastName+" is not verified==fail");
			}
		
		  
		 //step5: logout 
//		 Actions action1= new Actions(driver);
//		 action1.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//		 Thread.sleep(2000);
//		 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		 
		 driver.quit();
		
	}

}
