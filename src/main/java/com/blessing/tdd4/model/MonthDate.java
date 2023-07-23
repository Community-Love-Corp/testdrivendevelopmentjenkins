package com.blessing.tdd4.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class MonthDate {
    //private String month;
    private String month;
    private int year;
    private int monthEndDate;

    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthEndDate() {
        return monthEndDate;
    }
    public void setMonthEndDate(int monthEndDate) {
        monthEndDate = monthEndDate;
    }
    @Override
    public String toString() {
        return "MonthDates [month=" + month + ", year=" + year + ", MonthEndDate=" + monthEndDate + "]";
    } 
}
