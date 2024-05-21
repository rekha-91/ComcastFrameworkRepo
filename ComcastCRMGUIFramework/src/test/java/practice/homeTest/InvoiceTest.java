package practice.homeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Base_Class;

public class InvoiceTest extends Base_Class
{
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImplement.class)
	public void createInvoiceTest()
	{
		System.out.println("Execute Test");
		String ActTitle= driver.getTitle();
		Assert.assertEquals(ActTitle, " ");
		System.out.println("Step 1");
	}

}
