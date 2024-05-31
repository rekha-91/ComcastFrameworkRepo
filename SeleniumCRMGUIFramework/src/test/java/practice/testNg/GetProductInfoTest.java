package practice.testNg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest 
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName)
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.flipkart.com/");
		
		//search Product
		driver.findElement(By.name("q")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		String x="(//div[text()='"+productName+"'])[1]/../../div[2]/div[1]/div[1]/div[1]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		
	}
	@DataProvider
	public Object[] [] getData()
	{
		
		Object[][] objArray= new Object[3][2];
		objArray[0][0]= "vivo";
		objArray[0][1]= "vivo T3x 5G (Crimson Bliss, 128 GB)";
		objArray[1][0]= "vivo";
		objArray[1][1]= "vivo T3x 5G (Celestial Green, 128 GB)";
		objArray[2][0]= "vivo";
		objArray[2][1]= "vivo T2x 5G (Sunstone Orange, 128 GB)";
		return objArray;
	}

}
