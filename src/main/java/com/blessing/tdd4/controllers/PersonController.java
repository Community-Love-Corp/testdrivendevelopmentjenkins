package com.blessing.tdd4.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blessing.tdd4.LogicLayer.Login;
import com.blessing.tdd4.model.Credential;
import com.blessing.tdd4.model.Person;




@Controller
public class PersonController {
    
    @GetMapping("/people")
    String getPeople(Model model, @ModelAttribute("candidate1") Person person){
        System.out.println("new record on people page: "+person.getName()+" "+person.getAge());
        if(!person.getName().equals("")){
            model.addAttribute("people", person);   
        }else{
            System.out.println("invalid credentials provided");
            return "loginInvalid";   
        }
/*        model.addAttribute("something", "This is coming from the controller");
        model.addAttribute("people", Arrays.asList(
                new Person("John", 20),
                new Person("Sarah", 22),
                new Person("Simon", 23)
                ));*/
        return "people";
    }



    @GetMapping("/register")
    String register(Model model){
        Person Person = new Person();
        model.addAttribute("candidate", Person);
        return "register";
    }

    @PostMapping("/addRecordToDB")
    public String authenticateCredentials(@ModelAttribute("candidate") Person person, RedirectAttributes redirectAttributes){
        System.out.println("new record : "+person.getName()+" "+person.getAge());
        
        //boolean isValidUser = Login.authenticateCredentials(credential.getUsername(), credential.getPassword());
        if(!(person.getName().equals("") || person.getAge() == 0)){

            //List<Person> persons = Arrays.asList(new Person("John", 25), new Person("Jack", 35));
            //redirectAttributes.addFlashAttribute("persons", persons);
            redirectAttributes.addFlashAttribute("candidate1", person);
            //redirectAttributes.addFlashAttribute("creds", credential);
            //return "redirect:/authenticated";
            //redirectAttributes.addFlashAttribute("credential", credential);
             //model.addAttribute("credential", crendential);
            //session.setAttribute("username", credential.getUsername());
            //session.setAttribute("password", credential.getPassword());
            return "redirect:/people";
        }else{
            System.out.println("invalid credentials provided");
            return "loginInvalid";    
        }
    }

/*
        @GetMapping("/authenticated")
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

