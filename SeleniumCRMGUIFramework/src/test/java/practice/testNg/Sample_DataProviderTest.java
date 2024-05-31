package practice.testNg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sample_DataProviderTest
{
	@Test(dataProvider="getData")
	public void createContactTest(String firstName, String lastName)
	{
		System.out.println("FirstName: "+firstName+", LastName:"+lastName);
	}
	@DataProvider
	public Object[] [] getData()
	{
		Object[][] objArray= new Object[3][2];
		objArray[0][0]= "deepak";
		objArray[0][1]= "HR";
		objArray[1][0]= "Sanjay";
		objArray[1][1]= "Singh";
		objArray[2][0]= "Arya";
		objArray[2][1]= "Jay";
		return objArray;
	}

}
