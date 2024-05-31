package practice.pom.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTestWithoutPOM 
{
	public static void main(String[] args) 
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		
		WebElement usernameTF = driver.findElement(By.name("user_name"));
		WebElement passwordTF = driver.findElement(By.name("user_password"));
		WebElement loginButton = driver.findElement(By.id("submitButton"));
		
		usernameTF.sendKeys("admin");
		passwordTF.sendKeys("password");
		
		driver.navigate().refresh();// after refreshing page load and could able to identify old reference and it will give stale element reference.
		
		usernameTF.sendKeys("admin");
		passwordTF.sendKeys("password");
		loginButton.click();
	}

}
