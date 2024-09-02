package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        
        return "redirect:/home";
    }
    
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("home page handler");
        model.addAttribute("name", "Shubham Goswami");
        model.addAttribute("city", "Ghaziabad");
        
        return "home";
    }

    //about route
    @RequestMapping("/about")
    public String about(){
       System.out.println("About handler");
       
        return "about";
    }

    //service route
    @RequestMapping("/service")
    public String service(){
       System.out.println("service handler");
       
        return "service";
    }

    //contact route
    @RequestMapping("/contact")
    public String contact(){
       System.out.println("contact handler");
       
        return "contact";
    }

    //login route
    @GetMapping("/login")
    public String login(){
       System.out.println("login handler");
       
        return "login";
    }

    

    //signup route
    @RequestMapping("/signup")
    public String signup(Model model){
       System.out.println("signup handler");

       UserForm userForm = new UserForm();
        //default data
      
       //add data to model
        model.addAttribute("userForm", userForm);

        return "signup";
    }

    //processing register
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("processRegister handler");

       //fetch the form data
        System.out.println(userForm);

       //validate form data
        if(rBindingResult.hasErrors()){
            return "signup";
        }


       //save to database
       User u = new User();
       u.setName(userForm.getName());
       u.setEmail(userForm.getEmail());
       u.setPassword(userForm.getPassword());
       u.setAbout(userForm.getAbout());
       u.setPhoneNumber(userForm.getPhoneNumber());
       u.setProfilePic("/images/default_pfp.png");
       u.setEnabled(false);
       
       User savedUser = userService.saveUser(u);
       System.out.println("User saved");

       //message ="Registeration successful"
       Message msg =  Message.builder().content("Registration Successful").type(MessageType.green).build();
      
       session.setAttribute("message", msg);


       //redirect login page




        return"redirect:/signup";
   }
}
