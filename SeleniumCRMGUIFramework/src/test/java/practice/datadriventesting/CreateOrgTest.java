package practice.datadriventesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrgTest 
{
	public static void main(String[] args) throws IOException {
		
		File file1= new File("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\vtigerdata.properties");
		FileInputStream fis= new FileInputStream(file1);
		
		Properties pobj= new Properties();
		pobj.load(fis);
		
		String Browser = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String Username = pobj.getProperty("username");
		String Password = pobj.getProperty("password");
		
		/*
		 * System.out.println("Enter the Browser"); Scanner s= new Scanner(System.in);
		 * String browser = s.next();
		 */  //We should not use Scanner class in automation script coz according to rule of automation there should not be any manual intervention.
		
		WebDriver driver= null;
		
		if(Browser.equals("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(Browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		else
		{
		driver= new ChromeDriver();
		}
		
		driver.get(URL);
		
		WebElement usernameTextField = driver.findElement(By.id("username"));
		usernameTextField.clear();
		usernameTextField.sendKeys(Username);
		WebElement pwdTextField = driver.findElement(By.id("password"));
		pwdTextField.clear();
		pwdTextField.sendKeys(Password);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//a[@title='Calendar']")).click();
		
		driver.quit();
		
	}

}
