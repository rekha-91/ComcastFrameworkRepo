package com.comcast.crm.contacttest;

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

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest
{
	public static void main(String[] args) throws Throwable 
	{
		//reading data from property file
		FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		 String BROWSER= pobj.getProperty("browser");
		 String URL= pobj.getProperty("url");
		 String USERNAME= pobj.getProperty("username");
		 String PASSWORD= pobj.getProperty("password");
		 
		JavaUtility jlib= new JavaUtility();
		
		 //reading data from workbook
		 FileInputStream fis1= new FileInputStream("./testData/TestScriptData_01.xlsx");
		 Workbook wb= WorkbookFactory.create(fis1);
		 Sheet sheet = wb.getSheet("Contact");
		 Row row = sheet.getRow(1);
		 Cell cell = row.getCell(2);
		 String LastName = cell.getStringCellValue() + jlib.getRandomNumber();
		 
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
		 
		String startDate= jlib.getSystemDateYYYYMMDD();
		String endDate= jlib.getRequiredDateYYYYMMDD(30);
			
		 driver.findElement(By.name("lastname")).sendKeys(LastName);
		WebElement StartDateTF = driver.findElement(By.name("support_start_date"));
		Actions action= new Actions(driver);
		action.moveToElement(StartDateTF).perform();
		StartDateTF.clear();
		StartDateTF.sendKeys(startDate);
		WebElement EndDateTF = driver.findElement(By.name("support_end_date"));
		EndDateTF.clear();
		EndDateTF.sendKeys(endDate);
		
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		
		 //verify the phone number info expected result
		 String StartDatecheck = driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if(StartDatecheck.equals(startDate))
			{
				System.out.println(startDate+" is verified==pass");
			}
			else
			{
				System.out.println(startDate+" is not verified==fail");
			}
			//verify the End Date info expected result
					String EndDatecheck = driver.findElement(By.id("dtlview_Support End Date")).getText();
					if(EndDatecheck.equals(endDate))
					{
						System.out.println(endDate+" is verified==pass");
					}
					else
					{
						System.out.println(endDate+" is not verified==fail");
					}
		  
		 //step5: logout 
//		 Actions action= new Actions(driver);
//		 action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//		 Thread.sleep(2000);
//		 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		 
		 driver.quit();
		
	}

}
