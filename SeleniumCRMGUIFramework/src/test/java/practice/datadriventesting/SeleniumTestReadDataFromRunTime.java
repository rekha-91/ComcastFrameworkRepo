package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRunTime 
{
	@Test
	public void seleniumTest() throws IOException
	{
		
				 String BROWSER= System.getProperty("browser");
				 String URL= System.getProperty("url");
				 String USERNAME= System.getProperty("username");
				 String PASSWORD= System.getProperty("password");
				 
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
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.get(URL);
		 
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 
		 //step2: navigate to Organization module
		 driver.findElement(By.linkText("Organizations")).click();
		 
		 driver.quit();
	}

}
