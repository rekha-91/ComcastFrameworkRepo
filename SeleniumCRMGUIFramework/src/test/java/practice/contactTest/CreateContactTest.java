package practice.contactTest;

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

public class CreateContactTest
{
	public static void main(String[] args) throws Throwable 
	{
		
		//reading data from property file
		FileInputStream fis= new FileInputStream("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\test_data\\commondata.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		 String BROWSER= pobj.getProperty("browser");
		 String URL= pobj.getProperty("url");
		 String USERNAME= pobj.getProperty("username");
		 String PASSWORD= pobj.getProperty("password");
		 
		 Random random= new Random();
		 int randomInt = random.nextInt(1000);
		
		 //reading data from workbook
		 FileInputStream fis1= new FileInputStream("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\test_data\\TestScriptData_01.xlsx");
		 Workbook wb= WorkbookFactory.create(fis1);
		 Sheet sheet = wb.getSheet("Contact");
		 Row row = sheet.getRow(4);
		 Cell cell = row.getCell(2);
		 String LastName = cell.getStringCellValue() + randomInt;
		 
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
