package practiceTestNg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.comcast.crm.generic.fileutility.ExcelUtility;
public class GetProductInfoTest 
{
	@Test(dataProvider = "getData" )
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
		
		driver.quit();
	}
	@DataProvider
	public Object[] [] getData() throws Throwable
	{
		ExcelUtility elib= new ExcelUtility();
		int rowCount= elib.getRowCount("MobileProduct");
		Object[][] objArray= new Object[rowCount][2];
		
		for(int i=0; i<rowCount; i++)
		{
		objArray[i][0]= elib.getDataFromExcel("MobileProduct", i+1, 0);
		objArray[i][1]= elib.getDataFromExcel("MobileProduct", i+1, 1);
		}
		return objArray;
	}

}
