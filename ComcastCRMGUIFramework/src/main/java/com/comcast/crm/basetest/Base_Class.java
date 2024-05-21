package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class Base_Class
{
	 public WebDriver driver= null;
	 public static WebDriver sdriver= null;
	public DataBaseUtility dblib= new DataBaseUtility();
	public FileUtility flib= new FileUtility();
	public ExcelUtility elib= new ExcelUtility();
	public JavaUtility jlib= new JavaUtility();
	
	
	public WebDriverUtility wdLib= new WebDriverUtility();
	@BeforeSuite (groups= {"Smoke Test", "Regression Test"})
	public void configBS() throws Throwable
	{
		System.out.println("Connect to DB, Report Config");
		dblib.getDbconnection(null, null, null);
		
	}
	//@Parameters("BROWSER")
	@BeforeClass (groups= {"Smoke Test", "Regression Test"})
	public void configBC() throws Throwable
	{
		System.out.println("Launch the Browser");
		//reading data from property file
		 String BROWSER= flib.getDataFromPropertiesFile("browser");
		 
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
		 sdriver= driver;
		 UtilityClassObject.setDriver(driver);
	}
    @BeforeMethod(groups= {"Smoke Test", "Regression Test"})
    public void configBM() throws Throwable
    {
    	System.out.println("Login");
    	String URL= flib.getDataFromPropertiesFile("url");
		 String USERNAME= flib.getDataFromPropertiesFile("username");
		 String PASSWORD= flib.getDataFromPropertiesFile("password");
		 LoginPage lp= new LoginPage(driver);
		 lp.loginToApp(URL, USERNAME, PASSWORD);
    }
    @AfterMethod (groups= {"Smoke Test", "Regression Test"})
    public void configAM()
    {
    	HomePage hp=new HomePage(driver);
    	hp.logout();
    	
    }
    @AfterClass (groups= {"Smoke Test", "Regression Test"})
    public void configAC()
    {
    	System.out.println("Close the Browser");
    	driver.quit();
    }
    @AfterSuite (groups= {"Smoke Test", "Regression Test"})
    public void configAS() throws Throwable
    {
    	System.out.println("Close the DB Connection, ");
    	dblib.closeDbconnection();
    	
    }
}
