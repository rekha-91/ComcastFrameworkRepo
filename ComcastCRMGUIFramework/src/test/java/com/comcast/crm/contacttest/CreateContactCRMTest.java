package com.comcast.crm.contacttest;

/**
 * Test class for Contact Module
 * @author REKHA GUPTA
 */
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.Base_Class;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactCRMTest extends Base_Class {
	/**
	 * 
	 * Scenario: login()==>navigateContact()==>createContact()==>verify
	 */
	@Test(groups = "Smoke Test")
	public void createContactTest() throws Throwable {
		/* Step-1 reading data from workbook */
		String LastName = elib.getDataFromExcel("Contact", 4, 2) + jlib.getRandomNumber();

		/* Step-2 Navigating to Contact Module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step3: Navigating on Create Contact button */
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step4: Enter all the details & create new contacts */
		CreatingNewContactPage CCP = new CreatingNewContactPage(driver);
		CCP.getLastName().sendKeys(LastName);
		CCP.getSaveButton().click();

		/* verify the Start Date info expected result */
		String actualLastName = CCP.getActualLastName().getText();
		Assert.assertEquals(actualLastName, LastName);
	}

	@Test(groups = "Regression Test")
	public void createContactWithSupportDateTest() throws Throwable {
		/* Step-1 reading data from workbook */
		String LastName = elib.getDataFromExcel("Contact", 4, 2) + jlib.getRandomNumber();

		/* step2: Navigating to Contact Module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step3: click on Create Contact button */
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step4: Enter all the details & create new contacts */
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		CreatingNewContactPage CCP = new CreatingNewContactPage(driver);
		CCP.getLastName().sendKeys(LastName);
		CCP.createNewContactWithdate(startDate, endDate);

		CCP.getSaveButton().click();
		// wdLib.switchToAlertAndAccept(driver);
		// Thread.sleep(3000);
		/* verify the phone number info expected result */
		String StartDatecheck = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (StartDatecheck.equals(startDate)) {
			System.out.println(startDate + " is verified==pass");
		} else {
			System.out.println(startDate + " is not verified==fail");
		}
		/* verify the End Date info expected result */
		String EndDatecheck = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (EndDatecheck.equals(endDate)) {
			System.out.println(endDate + " is verified==pass");
		} else {
			System.out.println(endDate + " is not verified==fail");
		}

	}

	@Test(groups = "Regression Test")
	public void createContactWithOrgTest() throws Throwable {
		// step1: Login
		HomePage hp = new HomePage(driver);
		// step2: navigate to Organization module
		hp.getOrgLink().click();

		// reading data from workbook
		String OrgName = elib.getDataFromExcel("Contact", 1, 3) + jlib.getRandomNumber();
		String LastName = elib.getDataFromExcel("Contact", 1, 2) + jlib.getRandomNumber();
		// step3: click on Create Organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		// step4: Enter all the details & create new organization
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.createOrg(OrgName);
		// verify the header info expected result
		CreatingNewContactPage CCP = new CreatingNewContactPage(driver);
		String headerInfo = CCP.getHeaderInfo().getText();
		boolean status = headerInfo.contains(OrgName);
		SoftAssert softA = new SoftAssert();
		softA.assertEquals(status, true);
		// Assert.assertEquals(status, true); //Hard Assert
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		CCP.getLastName().sendKeys(LastName);
		CCP.getPlusImg().click();
		// Switch to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actURL = driver.getCurrentUrl();
			if (actURL.contains("module=Accounts&action")) {
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']")).click();

		// Switch to parent window
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);
			String actURL = driver.getCurrentUrl();
			if (actURL.contains("module=Contacts")) {
				break;
			}
		}
		CCP.getSaveButton().click();
		// verify the header organization name info expected result
		String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName);
		if (actualOrgName.trim().equals(OrgName)) {
			System.out.println(OrgName + " is verified==pass");
		} else {
			System.out.println(OrgName + " is not verified==fail");
		}
	}
}
