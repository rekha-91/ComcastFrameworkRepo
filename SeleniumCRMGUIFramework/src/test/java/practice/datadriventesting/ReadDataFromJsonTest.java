package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonTest
{
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
	{
		//step1: parse Json physical file in to java object using JsonParse class
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\test_data\\appCommonData.json"));
		
		//step2: Convert java Object in to JsonObject using down casting
		JSONObject map= (JSONObject) obj;   //downcasting jsonobject into java Object
		
		//step3: Get the value from json file using key
		System.out.println(map.get("url"));  //to store the data in form of object reference we will use toString() method and return type should be String.
		System.out.println(map.get("browser"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		System.out.println(map.get("timeout"));
	}

}
