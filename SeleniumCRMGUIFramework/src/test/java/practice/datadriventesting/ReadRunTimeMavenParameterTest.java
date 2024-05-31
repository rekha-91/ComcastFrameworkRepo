package practice.datadriventesting;

import org.testng.annotations.Test;

public class ReadRunTimeMavenParameterTest 
{
	@Test
	public void runtimeParameterTest()
	{
		String url = System.getProperty("url");
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		
		System.out.println("Env Data==>URL ==>"+url);
		System.out.println("Env Data==>BROWSER ==>"+browser);
		System.out.println("Env Data==>USERNAME ==>"+username);
		System.out.println("Env Data==>PASSWORD ==>"+password);
		
	}

	
}
