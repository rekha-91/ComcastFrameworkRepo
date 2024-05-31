package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnCondition 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		String expectedTestID= "tc_20";  //Providing expected condition
		String data1= " ";
		String data2= " ";
		String data3= " ";
		String data4= " ";
		boolean flag=false;
		FileInputStream	fis = new FileInputStream("C:\\\\Users\\\\REKHA GUPTA\\\\OneDrive\\\\Desktop\\\\test_data\\\\TestScriptData_01.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("org");
		int rowcount = sheet.getLastRowNum();
		
		for(int i=0; i<=rowcount; i++)
		{
			String data= " ";
			try {
				data= sheet.getRow(i).getCell(0).toString();
				if(data.equals(expectedTestID)) {
					flag=true;
					data1= sheet.getRow(i).getCell(1).toString();
					data2= sheet.getRow(i).getCell(2).toString();
					data3= sheet.getRow(i).getCell(3).toString();
					data4= sheet.getRow(i).getCell(4).toString();
				}
			}catch(Exception e) {}	
		}
		if(flag==true)
		{		
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		System.out.println(data4);
		}
		else
		{
			System.out.println(expectedTestID+" data is not available");
		}
		wb.close();
	}

}
