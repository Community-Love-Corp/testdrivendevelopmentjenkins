package com.blessing.tdd4.LogicLayer;


public interface IWebCalendar {
    public String GetWeekDay4MonthEnd(String month, String year);
    public String GetWeekDay4MonthStart(String month, String year);
    public String GetCurrentMonth();
    public int GetCurrentYear();
    public String GetCurrentMonthEndDate();
    public String GetMonthText(String MonthNumber); 
}
