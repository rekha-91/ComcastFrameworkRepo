package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationTest 
{
	public static void main(String[] args) throws Throwable {
		//Create Object
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
				 wdlib.waitForPageToLoad(driver);
				 driver.get(URL);
				// LoginPage lp = PageFactory.initElements(driver,LoginPage.class);  //Object initialization in test scripts
				 LoginPage lp= new LoginPage(driver);
			/*	lp.getUsernameTF().sendKeys(USERNAME);
				lp.getPasswordTF().sendKeys(PASSWORD);
				lp.getLoginButton().click();    */ //instead of writing in 3 step we can do this in 1 step
				 lp.loginToApp(USERNAME, PASSWORD); //this is called using business libraries
				 
				 //step2: navigate to Organization module
				 HomePage hp= new HomePage(driver);
				 hp.getOrgLink().click();
				 
				 //step3: click on Create Organization button
				 OrganizationPage op= new OrganizationPage(driver);
				 op.getCreateNewOrgBtn().click();
				 
				 //step4: Enter all the details & create new organization
				 CreateNewOrganizationPage NOP= new CreateNewOrganizationPage(driver);
				 NOP.createOrg(OrgName);
				 
				 //verify header msg expected result
				 OrganizationInfoPage oip= new OrganizationInfoPage(driver);
				String actOrgName=  oip.getHaedermsg().getText();
				if(actOrgName.contains(OrgName))
				{
					System.out.println(OrgName+" name is verified==pass");
				}
				else
				{
					System.out.println(OrgName+" name is not verified==fail");
				}
				 //verify the header organization name info expected result
				String ActualOrgText= oip.getViewOrganizationName().getText();
				if(ActualOrgText.equals(OrgName))
				{
					System.out.println(OrgName+" is verified==pass");
				}
				else
				{
					System.out.println(OrgName+" is not verified==fail");
				}
				  
				 //step5: logout 
				hp.logout();
				 driver.quit();
	}

}
