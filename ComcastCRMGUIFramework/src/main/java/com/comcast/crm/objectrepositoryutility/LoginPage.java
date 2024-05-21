package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility     
//Rule-1 create a separate java class
//Rule-2 Object creation
{
	WebDriver driver;
	public LoginPage(WebDriver driver) //we are creating constructor here and we can access this by creating object in script
	{
		this.driver= driver;
		PageFactory.initElements(driver, this); //this constructor will take-care of loading of objects
	}
	@FindBy (name="user_name")
	private WebElement usernameTF;
	
	@FindBy(name="user_password")
	private WebElement passwordTF;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	//Rule-3: Object Initialization- it should be done in test script not in POM class
    //Rule-4: Object Encapsulation
	public WebElement getUsernameTF() {
		return usernameTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	//Rule-5: provide Action(Business libraries)
	public void loginToApp(String username, String password)
	{
		driver.manage().window().maximize();
		waitForPageToLoad(driver);
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginButton.click();
	}
	public void loginToApp(String url, String username, String password)
	{
		driver.manage().window().maximize();
		waitForPageToLoad(driver);
		driver.get(url);
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginButton.click();
	}
}
