package practice.orgTest;

import java.io.FileInputStream;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.Select;

public class CreateOrgWithIndustryTest
{
	public static void main(String[] args) throws Throwable {
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
				 Sheet sheet = wb.getSheet("org");
				 Row row = sheet.getRow(4);
				 Cell cell = row.getCell(2);
				 Cell cellind = row.getCell(3);
				 Cell cellType = row.getCell(4);
				 String OrgName = cell.getStringCellValue() + randomInt;
				 String IndName = cellind.getStringCellValue();
				 String TypeName= cellType.getStringCellValue();
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
				WebElement IndustrySelect= driver.findElement(By.name("industry"));
				 Select sl1= new Select(IndustrySelect);
				 sl1.selectByValue(IndName);
				 
				 WebElement TypeSelect = driver.findElement(By.name("accounttype"));
				 Select sl2= new Select(TypeSelect);
				 sl2.selectByValue(TypeName);
				 
				 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //verify header msg expected result
				String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(OrgName))
				{
					System.out.println(OrgName+" is created==pass");
				}
				else
				{
					System.out.println(OrgName+" is not created==fail");
				}
				 //verify the header organization name info expected result
				String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(actualOrgName.equals(OrgName))
				{
					System.out.println(OrgName+" is created==pass");
				}
				else
				{
					System.out.println(OrgName+" is not created==fail");
				}
				
				//verify the drop down industry name info expected result
				String actualIndustryName = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actualIndustryName.equals(IndName))
				{
					System.out.println(IndName+" is created==pass");
				}
				else
				{
					System.out.println(IndName+" is not created==fail");
				}
				
				//verify the drop down industryType name info expected result
				String actualIndustryTypeName = driver.findElement(By.id("dtlview_Type")).getText();
				if(actualIndustryTypeName.equals(TypeName))
				{
					System.out.println(TypeName+" is created==pass");
				}
				else
				{
					System.out.println(TypeName+" is not created==fail");
				}
				 //step5: logout 
				 Actions action= new Actions(driver);
				 action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				 Thread.sleep(2000);
				 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				 
				 driver.quit();
	}

}
