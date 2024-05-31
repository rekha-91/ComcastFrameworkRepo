package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//step:1 get the excel path location & java object of the physical Excel file
		FileInputStream fis = new FileInputStream("C:\\Users\\REKHA GUPTA\\OneDrive\\Desktop\\test_data\\TestScriptData_01.xlsx");
		
		//step:2 Open workbook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//step3: get the control of the "org" sheet
		Sheet sh = wb.getSheet("org");
		
		//step:4 get the control of the "1st" row
		Row ro = sh.getRow(2);
		
		//step:5 get the control of the 2 cell & read the string data
	     Cell ce = ro.getCell(2);
	     String celldata = ce.getStringCellValue();
	    Cell cel1 = ro.getCell(3);
	    int cellvalue = (int) cel1.getNumericCellValue();
	 	System.out.println("celldata: "+celldata);
	 	System.out.println("cellvalue: "+cellvalue);
	     
	    //step6: close the workbook
	 	wb.close();
	
		
	}

}

