package datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadTheDataFromPropertyFile {

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis = new FileInputStream("./src/test/resource/vtigerdata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String applicationUrl = pobj.getProperty("url");
		System.out.println("applicationUrl = "+applicationUrl);
	}

}
