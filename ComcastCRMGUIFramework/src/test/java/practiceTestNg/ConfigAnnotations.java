package practiceTestNg;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfigAnnotations 
{
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Execute Before Suite");
	}
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Execute Before Class");
	}
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Execute before method");
	}
	@Test
	public void createContact()
	{
		System.out.println("Execute Create Contact");
	}
	@Test
	public void createContactDate()
	{
		System.out.println("Execute Create Contact Date");
	}
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("Execute after method");
	}
	@AfterClass
	public void afterClass()
	{
		System.out.println("Execute after class");
	}
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("Execute After Suite");
	}

}
