package practice.homeTest;
/**
 * @author REKHA GUPTA
 */
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.Base_Class;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerUtility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrgCRMTest extends Base_Class {
	@Test(groups = "Smoke Test")
	public void createOrgTest() throws Throwable {
		/* Step-1 Read test script data from excel file */
		UtilityClassObject.getTest().log(Status.INFO, "Read the data from Excel");
		String OrgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();

		/* Step-2 Navigating to Organization module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		/* Step3- Navigating to Create Organization page */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create Org Page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		ListImpClass.test.log(Status.INFO, "Create a new Organisation ");
		CreateNewOrganizationPage NOP = new CreateNewOrganizationPage(driver);
		NOP.createOrg(OrgName);
		UtilityClassObject.getTest().log(Status.INFO, OrgName + "New Org Created");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHaedermsg().getText();
		if (actOrgName.contains(OrgName)) {
			System.out.println(OrgName + " name is verified==pass");
		} else {
			System.out.println(OrgName + " name is not verified==fail");
		}
		/* verify the header organization name info expected result */
		String ActualOrgText = oip.getViewOrganizationName().getText();
		if (ActualOrgText.equals(OrgName)) {
			System.out.println(OrgName + " is verified==pass");
		} else {
			System.out.println(OrgName + " is not verified==fail");
		}

	}

	@Test(groups = "Regression Test")
	public void createOrgWithPhoneNoTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "Read the data from Excel");
		String OrgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String PhoneNo = elib.getDataFromExcel("org", 7, 3) + jlib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		CreateNewOrganizationPage NOP = new CreateNewOrganizationPage(driver);
		NOP.createOrgWithPhoneNo(OrgName, PhoneNo);
		/* verify the phone number info expected result */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String ActPhoneNo = oip.getActuaPhoneNum().getText();
		if (ActPhoneNo.equals(PhoneNo)) {
			System.out.println(PhoneNo + " is created==pass");
		} else {
			System.out.println(PhoneNo + " is not created==fail");
		}
	}

	@Test(groups = "Regression Test")
	public void createOrgWithIndustryTest() throws Throwable {
		String OrgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String IndustryName = elib.getDataFromExcel("org", 4, 3);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		CreateNewOrganizationPage NOP = new CreateNewOrganizationPage(driver);
		NOP.createOrg(OrgName, IndustryName);
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHaedermsg().getText();
		if (actOrgName.contains(OrgName)) {
			System.out.println(OrgName + " name is verified==pass");
		} else {
			System.out.println(OrgName + " name is not verified==fail");
		}

	}

}
