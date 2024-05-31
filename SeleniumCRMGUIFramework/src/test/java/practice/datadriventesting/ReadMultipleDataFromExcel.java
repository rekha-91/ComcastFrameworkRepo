package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\test_data\\TestScriptData_01.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheet = wb.getSheet("product");
		 
		int rowcount = sheet.getLastRowNum();
		 for(int i=1; i<=rowcount; i++)
		 {
			 Row row = sheet.getRow(i);	 
		String column1Data = row.getCell(1).toString();
		String column2Data = row.getCell(2).toString();
		 System.out.println(column1Data+": "+column2Data);
		 } 
		 wb.close();
	}

}
