package practice.homeTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Base_Class;

//@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class SampleTest extends Base_Class {
	@Test
	public void createSampleTest() {
		System.out.println("Execute create Sample Test ");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");

	}

	@Test
	public void createSample2Test() {
		System.out.println("Execute create Sample2 Test ");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
