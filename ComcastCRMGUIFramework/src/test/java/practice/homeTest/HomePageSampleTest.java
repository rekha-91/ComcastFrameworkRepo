package practice.homeTest;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class HomePageSampleTest 
{
	@Test
	public void homepageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		System.out.println(mtd.getName()+" Test End");
	}

	@Test
	public void verifyLogo(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");
		Reporter.log("Step-1",true);
		Reporter.log("Step-2",true);
		Reporter.log("Step-3",true);
		Reporter.log("Step-4",true);
		Reporter.log(mtd.getName()+" Test End",true);
	}

}
