package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	@FindBy(className= "dvHeaderText")
	private WebElement haedermsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement ViewOrganizationName;
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actuaPhoneNum;
	
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getHaedermsg() {
		return haedermsg;
	}

	public WebElement getViewOrganizationName() {
		return ViewOrganizationName;
	}
	
	
	public WebElement getActuaPhoneNum() {
		return actuaPhoneNum;
	}

	public void orgCick()
	{
		getOrgLink().click();
	}
	

	
}
