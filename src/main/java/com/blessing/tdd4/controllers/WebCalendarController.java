package com.blessing.tdd4.controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blessing.tdd4.LogicLayer.Login;
import com.blessing.tdd4.LogicLayer.WebCalendarLocalService;
import com.blessing.tdd4.model.Credential;
import com.blessing.tdd4.model.DropDownListElement;
import com.blessing.tdd4.model.MonthDate;
import com.blessing.tdd4.model.Person;

import jakarta.servlet.http.HttpSession;




@Controller
@SessionAttributes("credentialObj")
public class WebCalendarController {

@Autowired
private WebCalendarLocalService calendarService;

    @GetMapping("/calendar")
    String getCalendar(Model model,@ModelAttribute("monthdate") MonthDate md, HttpSession session,
                        @ModelAttribute("creds") Credential creds, RedirectAttributes redirectAttributes){
        System.out.println("From getCalendar method - username: "+creds.getUsername()+ " password: "+creds.getPassword());
        //boolean isValidUser = Login.authenticateCredentials((String)session.getAttribute("username"), (String)session.getAttribute("password"));
        if (md.getMonth()==null){
            session.setAttribute("credentialObj", creds);
        }
        Credential LogonCreds = (Credential)session.getAttribute("credentialObj");
        boolean isValidUser = Login.authenticateCredentials(LogonCreds.getUsername(), LogonCreds.getPassword());
        if(!isValidUser){
            return "sessionInvalid";
        }
        //if first landing on page, use default (current date) to present content 
        String Month, MonthEndDate; 
        int Year;
        if (md.getMonth()==null){
            Month = calendarService.GetCurrentMonth();
            Year = calendarService.GetCurrentYear();
            MonthEndDate = calendarService.GetCurrentMonthEndDate();
            session.setAttribute("credentialObj", creds);
        }else{
            Month = md.getMonth();
            Year = md.getYear();
           MonthEndDate = calendarService.GetDate4MonthEnd(Month,Year+"");
        }
        String FullMonthString = calendarService.GetMonthText(Month);
        String weekdayFirstDay = calendarService.GetWeekDay4MonthStart(Month, Year+"");
        String weekdayLastDay = calendarService.GetWeekDay4MonthEnd(Month, Year+"");
        model.addAttribute("MonthText", FullMonthString);
        model.addAttribute("weekdayFirstDayAt", weekdayFirstDay);
        model.addAttribute("weekdayLastDayAt", weekdayLastDay);    
        List<DropDownListElement> options = populateMonthList();
        model.addAttribute("options", options);
        List<DropDownListElement> yearOptions = populateYearList();
        model.addAttribute("yearOptions", yearOptions);
        System.out.println(options);
        System.out.println(yearOptions);
        System.out.println("Date today: "+Month+"/"+Year+", Month end date is:"+MonthEndDate + " and first weekday is "+weekdayFirstDay+ "and last weekday is "+weekdayLastDay);
        model.addAttribute("monthdate", new MonthDate(Month, Year, Integer.parseInt(MonthEndDate)));
        //also pass session variable
        model.addAttribute("credentialObj", new Credential (creds.getUsername(), creds.getPassword()));
        //redirectAttributes.addFlashAttribute("creds", new Credential (creds.getUsername(), creds.getPassword()));
        
        //return "redirect:/calendar";
        return "webcalendar";
    }



    @PostMapping("/getMonthFirstAndLastWeekdays")
    public String UserSelectionForm(@ModelAttribute("monthdate") MonthDate md, 
                                                    RedirectAttributes redirectAttributes){
        System.out.println(md);
        //Credential cred = (Credential)session.getAttribute("credentialObj");
        //System.out.println(creds);
        //calendarService.                                                

        String month = md.getMonth();
        int year = md.getYear();
        MonthDate md1 = new MonthDate();
        md1.setMonth(month);
        md1.setYear(year);
        //if(isValidUser){

        //    List<Person> persons = Arrays.asList(new Person("John", 25), new Person("Jack", 35));
        //   redirectAttributes.addFlashAttribute("persons", persons);
        redirectAttributes.addFlashAttribute("monthdate", md1);   
        //return "webcalendar";
        return "redirect:/calendar";
        //}else{
        //    System.out.println("invalid user");
        //    return "people";    
        //}
    }

    public List<DropDownListElement> populateMonthList() {
        List<DropDownListElement> options = new ArrayList<DropDownListElement>();
        options.add(new DropDownListElement("01","January"));
        options.add(new DropDownListElement("02","February"));
        options.add(new DropDownListElement("03","March"));
        options.add(new DropDownListElement("04","April"));
        options.add(new DropDownListElement("05","May"));
        options.add(new DropDownListElement("06","June"));
        options.add(new DropDownListElement("07","July"));
        options.add(new DropDownListElement("08","August"));
        options.add(new DropDownListElement("09","September"));
        options.add(new DropDownListElement("10","October"));
        options.add(new DropDownListElement("11","November"));
        options.add(new DropDownListElement("12","December"));
        return options;
    }

    public List<DropDownListElement> populateYearList() {
        List<DropDownListElement> options = new ArrayList<DropDownListElement>();
        options.add(new DropDownListElement("2021","2021"));
        options.add(new DropDownListElement("2022","2022"));
        options.add(new DropDownListElement("2023","2023"));
        options.add(new DropDownListElement("2024","2024"));
        options.add(new DropDownListElement("2025","2025"));
        options.add(new DropDownListElement("2026","2026"));
        return options;
    }

/*    @GetMapping("/authenticated")
    String showPeople(Model model, @ModelAttribute("persons") Object persons, @ModelAttribute("creds") Credential credential){
     //   model.addAttribute("something", "This is coming from the controller");
     //   model.addAttribute("people", Arrays.asList(
     //           new Person("John", 20),
     //           new Person("Sarah", 22),
     //           new Person("Simon", 23)
     //           ));
        //Person p1 = new Person("Jack", 35);
        List<Person> PersonList = (List<Person>) persons;
        System.out.println(PersonList.size());
        System.out.println("username: "+ credential.getUsername());
        model.addAttribute("people",PersonList); 
        return "people";
    }
    */
}
