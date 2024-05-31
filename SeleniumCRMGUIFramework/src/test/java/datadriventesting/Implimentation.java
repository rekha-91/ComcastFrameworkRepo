package datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Implimentation 
{
	public static WebDriver driver;
	 public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resource/vtigerdata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String browserName = pobj.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			driver= new ChromeDriver();
		}else if(browserName.equals("firefox"))
		{
			driver= new FirefoxDriver();
		} else if(browserName.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(pobj.getProperty("url"));
		Thread.sleep(1000);
		WebElement usernameTF = driver.findElement(By.id("username"));
		usernameTF.clear();
		Thread.sleep(1000);
		usernameTF.sendKeys(pobj.getProperty("username"));
		Thread.sleep(1000);
		WebElement passwordTF = driver.findElement(By.id("password"));
		passwordTF.clear();
		Thread.sleep(1000);
		passwordTF.sendKeys(pobj.getProperty("password"));
		Thread.sleep(1000);
		WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
		signinButton.click();
		Thread.sleep(1000);
		driver.manage().window().minimize();
		driver.quit();	}

}
