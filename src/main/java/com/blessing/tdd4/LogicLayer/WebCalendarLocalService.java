package com.blessing.tdd4.LogicLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component

public class WebCalendarLocalService implements IWebCalendar{

//@Autowired	
//private Calendar cal;
//@Autowired
private LocalDateTime now;
//@Autowired
//private LocalDate ld;


	public WebCalendarLocalService(){
		now = LocalDateTime.now();		
	}

    public String GetWeekDay4MonthEnd(String month, String year) {
       	   Calendar c = null;
		try {
			c = getCurrentMonthCalendarObj(month, year); // this takes current date
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   c.add(Calendar.MONTH, 1);  
       c.set(Calendar.DAY_OF_MONTH, 1);  
       c.add(Calendar.DATE, -1);  
	   System.out.println(c.getTime());       // this returns java.util.Date
	   DateFormat day = new SimpleDateFormat("EEEE");
	   String weekday = day.format(c.getTime());
	   DateFormat day2 = new SimpleDateFormat("dd/MM/yyyy");
	   //DateFormat day2 = new SimpleDateFormat("dd/MMMM/yyyy");
       String date = day2.format(c.getTime());
	   /*HelloWorldService_Service helloService = new HelloWorldService_Service();
		HelloWorldService hello = helloService.getHelloWorld();
		String weekday = hello.getMonthEndDay(getMonth(), getYear());
		String date = hello.getMonthEndDate(getMonth(), getYear());*/
	   //setMonthEndDate(weekday+ " "+date);
	   return weekday+ " "+date;
    }

	public String GetDate4MonthEnd(String month, String year) {
       	   Calendar c = null;
		try {
			c = getCurrentMonthCalendarObj(month, year); // this takes current date
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   c.add(Calendar.MONTH, 1);  
       c.set(Calendar.DAY_OF_MONTH, 1);  
       c.add(Calendar.DATE, -1);  
	   System.out.println(c.getTime());       // this returns java.util.Date
	   DateFormat day2 = new SimpleDateFormat("dd");
	   //DateFormat day2 = new SimpleDateFormat("dd/MMMM/yyyy");
       String date = day2.format(c.getTime());
	   /*HelloWorldService_Service helloService = new HelloWorldService_Service();
		HelloWorldService hello = helloService.getHelloWorld();
		String weekday = hello.getMonthEndDay(getMonth(), getYear());
		String date = hello.getMonthEndDate(getMonth(), getYear());*/
	   //setMonthEndDate(weekday+ " "+date);
	   return date;
    }
	


    public String GetWeekDay4MonthStart(String month, String year) {
	   Calendar c = null;
		System.out.println("month Jay: "+month);
	   	try {
			c = getCurrentMonthCalendarObj(month, year); // this takes current date
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	   c.set(Calendar.DAY_OF_MONTH, 1);
	   System.out.println(c.getTime());       // this returns java.util.Date
	   DateFormat day = new SimpleDateFormat("EEEE");
	   String weekday = day.format(c.getTime());
	   DateFormat day2 = new SimpleDateFormat("dd/MM/yyyy");
	   String date = day2.format(c.getTime());
	   /*HelloWorldService_Service helloService = new HelloWorldService_Service();
		HelloWorldService hello = helloService.getHelloWorld();
		String weekday = hello.getMonthStartDay(getMonth(), getYear());
		String date = hello.getMonthStartDate(getMonth(), getYear());*/
	   return weekday+ " "+date;

    }

	// get a calendar object referring to the month specified in parameter string
	public Calendar getCurrentMonthCalendarObj(String FullMonthText, String year) throws Throwable{
		//String FullMonthText = GetMonthText(TwoLetterMonth);
		String dateInput = "01/"+FullMonthText+"/"+year;			
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = df.parse(dateInput);
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.setTime(startDate); // set date to specified in arguement
		return c;
	}



	public String GetCurrentMonth() {
		//System.out.println(new SimpleDateFormat("MMMM").format(cal.getTime()));
		//return (new SimpleDateFormat("MMMM").format(cal.getTime()));
		return GetMonth_MM_String(now.getMonthValue());

		//return (new LocalDate().montho)
	}

	public String GetMonth_MM_String(int month){
		if (month<10){//.monthOfYear.getAsText();
			return "0"+month;
		}else{
			return month+"";
		}
	}


	public int GetCurrentYear() {
		//return (new SimpleDateFormat("YYYY").format(cal.getTime()));
		
		return now.getYear();//.monthOfYear.getAsText();

	}


	public String GetCurrentMonthEndDate() {
		//return GetDate4MonthEnd(new SimpleDateFormat("MM"),GetCurrentYear());
		//return now.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth();
		String month = GetCurrentMonth();
		int year = GetCurrentYear();
		Calendar c = null;
		try {
			c = getCurrentMonthCalendarObj(month, year+ ""); // this takes current date
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   c.add(Calendar.MONTH, 1);  
       c.set(Calendar.DAY_OF_MONTH, 1);  
       c.add(Calendar.DATE, -1);  
	   System.out.println(c.getTime());       // this returns java.util.Date
	   //DateFormat day = new SimpleDateFormat("EEEE");
	   //String weekday = day.format(c.getTime());
	   DateFormat day2 = new SimpleDateFormat("dd");
	   //DateFormat day2 = new SimpleDateFormat("dd/MMMM/yyyy");
       String date1 = day2.format(c.getTime());
	   /*HelloWorldService_Service helloService = new HelloWorldService_Service();
		HelloWorldService hello = helloService.getHelloWorld();
		String weekday = hello.getMonthEndDay(getMonth(), getYear());
		String date = hello.getMonthEndDate(getMonth(), getYear());*/
	   //setMonthEndDate(weekday+ " "+date);
	   return date1;

	}

	// Given a month in integer, return the full name of the month
	public String GetMonthText(String month) {
		System.out.println("GetMonthText: " +month);
		return now.withMonth(Integer.parseInt(month)).getMonth().name();
	}


}
