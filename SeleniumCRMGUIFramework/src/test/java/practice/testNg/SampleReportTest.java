package practice.testNg;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest
{
	@Test
	public void createContactTest()
	{
		//spark report configuration
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add Env information & create Test
		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-120");
		ExtentTest test= report.createTest("createContactTest");
		
		test.log(Status.INFO,"Login to APP");
		test.log(Status.INFO,"Navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HDFC"))
		{
		test.log(Status.PASS,"Contact is created");
		}
		else
		{
			test.log(Status.FAIL, "Contact is not created");
		}
		report.flush();
	}
	

}
