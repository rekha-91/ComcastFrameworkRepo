package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage
{
	WebDriver driver;
	public OrganizationPage(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(xpath= "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchField;
	
	@FindBy(name="search_field")
	private WebElement searchdropdown;
	
	@FindBy(name="submit")
	private WebElement searchButton;
	
	public WebElement getCreateNewOrgBtn() {
		
		return createNewOrgBtn;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getSearchdropdown() {
		return searchdropdown;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	

	
}
