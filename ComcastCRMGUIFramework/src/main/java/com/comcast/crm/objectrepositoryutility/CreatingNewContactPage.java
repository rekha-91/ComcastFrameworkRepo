package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreatingNewContactPage
{
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	@FindBy(name="lastname")
	private WebElement LastName;
	@FindBy(name="button")
	private WebElement SaveButton;
	@FindBy(id = "dtlview_Last Name")
	private WebElement TextLastName;
	@FindBy(name="support_start_date")
	private WebElement StartDateTF;
	@FindBy(name="support_end_date")
	private WebElement EndDateTF;
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement plusImg;
	@FindBy(id = "dtlview_Last Name")
	private WebElement actualLastName;
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfo;
	
	
	public WebElement getLastName()
	{
		return LastName;
	}
	public WebElement getSaveButton()
	{
		return SaveButton;
	}
	public WebElement getTextLastName() {
		return TextLastName;
	}
	public WebElement getStartDateTF() {
		return StartDateTF;
	}
	public WebElement getEndDateTF() {
		return EndDateTF;
	}
	
    
	public WebElement getHeaderInfo() {
		return headerInfo;
	}
	public WebElement getActualLastName() {
		return actualLastName;
	}
	public WebElement getPlusImg() {
		return plusImg;
	}
	public void createNewContactWithdate(String startDate, String endDate)
	{
		Actions action= new Actions(driver);
		action.moveToElement(StartDateTF).perform();
		StartDateTF.clear();
		StartDateTF.sendKeys(startDate);
		EndDateTF.clear();
		EndDateTF.sendKeys(endDate);
	}
	
	
	
}
