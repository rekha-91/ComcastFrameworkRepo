package practice.basics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateBasic 
{
	public static void main(String[] args) 
	{
		Date dateobj= new Date();
		//System.out.println(dateobj);
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String actDate = sim.format(dateobj);
		System.out.println("Actual Date: "+actDate);
	
		Calendar calender = sim.getCalendar();
		calender.add(Calendar.DAY_OF_MONTH, 30);
		String dateRequires = sim.format(calender.getTime());
		System.out.println("Before date: "+dateRequires);
		
		
	}

}
