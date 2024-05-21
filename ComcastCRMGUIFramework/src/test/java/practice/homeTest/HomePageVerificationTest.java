package practice.homeTest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageVerificationTest
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+ " Test Start");
		String expectedPage="Home";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		String actualTitle= driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//Hard Assert
		Assert.assertEquals(actualTitle, expectedPage);
		driver.close();
		System.out.println(mtd.getName()+ " Test End");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+ " Test Start");
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		//HardAssert
	//	Assert.assertTrue(status);
		//Soft Assert
		SoftAssert as= new SoftAssert();
		as.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName()+ " Test End");
		
	}
	
}
