package practice.homeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrgTest 
{
	public static void main(String[] args) throws Throwable {
		
	FileUtility flib= new FileUtility();
	ExcelUtility elib= new ExcelUtility();
	JavaUtility jlib= new JavaUtility();
	WebDriverUtility wdlib= new WebDriverUtility();
//reading data from property file
	 String BROWSER= flib.getDataFromPropertiesFile("browser");
	 String URL= flib.getDataFromPropertiesFile("url");
	 String USERNAME= flib.getDataFromPropertiesFile("username");
	 String PASSWORD= flib.getDataFromPropertiesFile("password");
	
	 //reading data from workbook
	String OrgName= elib.getDataFromExcel("org", 4, 2)+ jlib.getRandomNumber();
		
     WebDriver driver=null;
	 
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
	 wdlib.waitForPageToLoad(driver);
	 driver.get(URL);
	 LoginPage lp= new LoginPage(driver);
	 lp.loginToApp(USERNAME, PASSWORD); 

	 //step2: navigate to Organization module
	 HomePage hp= new HomePage(driver);
	 hp.getOrgLink().click();
	 
	 //step3: click on Create Organization button
	 OrganizationPage op= new OrganizationPage(driver);
	 op.getCreateNewOrgBtn().click();
	 
	 //step4: Enter all the details & create new organization
	 CreateNewOrganizationPage NOP= new CreateNewOrganizationPage(driver);
	 NOP.createOrg(OrgName);
	
	 driver.navigate().refresh();
	 OrganizationInfoPage oif= new OrganizationInfoPage(driver);
	 oif.orgCick();
	 
	 //search for organization
	 op.getSearchField().sendKeys(OrgName);
	 wdlib.select(op.getSearchdropdown(), "Organization Name");
	 op.getSearchButton().click();
	 
	 //In dynamic web element select & delete org
	 driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../td[8]/a[text()='del']")).click();
	 wdlib.switchToAlertAndAccept(driver);
	 //step5: logout 
	hp.logout();
	 driver.quit();
}
}
