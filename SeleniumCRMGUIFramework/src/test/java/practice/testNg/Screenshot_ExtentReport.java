package practice.testNg;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Screenshot_ExtentReport
{
	ExtentReports report;
	@BeforeSuite
	public void configBS()
	{
		//spark report configuration
				ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM Test Suite Results");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				//Add Env information & create Test
			    report= new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-10");
				report.setSystemInfo("BROWSER", "CHROME-120");
	}
	@Test
	public void createContactTest()
	{
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8888");
		//take screenshot
		TakesScreenshot ts= (TakesScreenshot) driver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test= report.createTest("createContactTest");
		
		test.log(Status.INFO,"Login to APP");
		test.log(Status.INFO,"Navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HJFC"))
		{
		test.log(Status.PASS,"Contact is created");
		}
		else
		{
		test.addScreenCaptureFromBase64String(filePath, "ErrorFile");	
		}
	driver.close();	
	}
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}


}
