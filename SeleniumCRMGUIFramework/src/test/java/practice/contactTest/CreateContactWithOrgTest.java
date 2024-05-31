package practice.contactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrgTest {

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
		 Row row = sheet.getRow(1);
		 Cell cell = row.getCell(3);
		 Cell cel1 = row.getCell(2);
		 String OrgName = cell.getStringCellValue() + randomInt;
		 String LastName = cel1.getStringCellValue() + randomInt;
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
		 driver.findElement(By.linkText("Organizations")).click();
		 
		 //step3: click on Create Organization button
		 driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		 
		 //step4: Enter all the details & create new organization
		 driver.findElement(By.name("accountname")).sendKeys(OrgName);
		 
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		 //verify the header info expected result
		 String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(OrgName))
			{
				System.out.println(OrgName+" Header is verified==pass");
			}
			else
			{
				System.out.println(OrgName+" Header is not verified==fail");
			}

		 Thread.sleep(2000);
		 //step5: navigate to Organization module
		 driver.findElement(By.linkText("Contacts")).click();
		 
		 //step6: click on Create Contact button
		 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		 
		 //step4: Enter all the details & create new contacts
		 driver.findElement(By.name("lastname")).sendKeys(LastName);
		 driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		 
		 //Switch to child window
		 Set<String> set = driver.getWindowHandles();
		 Iterator<String> it = set.iterator();
		 while(it.hasNext())
		 {
			 String windowID = it.next(); 
			 driver.switchTo().window(windowID);
			 String actURL = driver.getCurrentUrl();
			 if(actURL.contains("module=Accounts&action"))
			 {break;}
		 }
		 driver.findElement(By.name("search_text")).sendKeys(OrgName);
		 driver.findElement(By.name("search")).click();
		 driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
		 
		//Switch to parent window
		 Set<String> set1 = driver.getWindowHandles();
		 Iterator<String> it1 = set.iterator();
		 while(it1.hasNext())
		 {
			 String windowID = it1.next(); 
			 driver.switchTo().window(windowID);
			 String actURL = driver.getCurrentUrl();
			 if(actURL.contains("module=Contacts"))
			 {break;}
		 }
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		
			 //verify the header organization name info expected result
			String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			System.out.println(actualOrgName);
			if(actualOrgName.trim().equals(OrgName))
			{
				System.out.println(OrgName+" is verified==pass");
			}
			else
			{
				System.out.println(OrgName+" is not verified==fail");
			}
		  
		 //step5: logout 
		 
		 
		 driver.quit();
		

	}

}
