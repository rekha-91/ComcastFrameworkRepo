package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage 
{
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	@FindBy(name="accountname")
	private WebElement OrganizationName;
	
	@FindBy(name="industry")
	private WebElement IndustryName;
	
	@FindBy(id="phone")
	private WebElement Phone;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	public WebElement getOrganizationName() {
		return OrganizationName;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void createOrg(String OrgName)
	{
		OrganizationName.sendKeys(OrgName);
		saveButton.click();
	}
	public void createOrgWithPhoneNo(String OrgName,String PhoneNo)
	{
		OrganizationName.sendKeys(OrgName);
		Phone.sendKeys(PhoneNo);
		saveButton.click();
	}
	
	public void createOrg(String OrgName, String industry)
	{
		OrganizationName.sendKeys(OrgName);
		Select sel= new Select(IndustryName);
		sel.selectByVisibleText(industry);
		saveButton.click();
	}
	
	

}
