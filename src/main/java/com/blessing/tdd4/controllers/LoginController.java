package com.blessing.tdd4.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blessing.tdd4.model.Credential;
import com.blessing.tdd4.model.Person;

import jakarta.servlet.http.HttpSession;

import com.blessing.tdd4.LogicLayer.Login;
import java.util.Arrays;


@Controller
public class LoginController {
    
    @GetMapping
    String login(Model model){
        Credential crendential = new Credential();
        model.addAttribute("credential", crendential);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateCredentials(@ModelAttribute("credential") Credential credential, RedirectAttributes redirectAttributes){
        System.out.println(credential);
        boolean isValidUser = Login.authenticateCredentials(credential.getUsername(), credential.getPassword());
        if(isValidUser){

            //List<Person> persons = Arrays.asList(new Person("John", 25), new Person("Jack", 35));
            //redirectAttributes.addFlashAttribute("persons", persons);
            redirectAttributes.addFlashAttribute("creds", credential);
            //return "redirect:/authenticated";
            //redirectAttributes.addFlashAttribute("credential", credential);
             //model.addAttribute("credential", crendential);
            //session.setAttribute("username", credential.getUsername());
            //session.setAttribute("password", credential.getPassword());
            return "redirect:/calendar";
        }else{
            System.out.println("invalid credentials provided");
            return "loginInvalid";    
        }
    }

}
