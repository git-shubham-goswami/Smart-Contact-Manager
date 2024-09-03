package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;

import java.util.*;
import java.security.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
   
    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

   

    // User dashboard
    @GetMapping("/dashboard")
    public String userDashboard(Model model, Authentication authentication) {
        // Get logged-in user's email
        String email = Helper.getEmailOfLoggedInUser(authentication);

        // Fetch user details
        User user = userService.getUserByEmail(email);

        // Fetch contact statistics and recent contacts
        long totalContacts = contactService.getByUser(user, 0, Integer.MAX_VALUE, "name", "asc").getTotalElements();
        List<Contact> recentContacts = contactService.getByUser(user, 0, 5, "createdDate", "desc").getContent();

        // Add attributes to the model
        model.addAttribute("loggedInUser", user);
        model.addAttribute("totalContacts", totalContacts);
        model.addAttribute("recentContacts", recentContacts);

        return "user/dashboard";
    }


    //user profile
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication){
      
        return "user/profile";
    }

    //user add contact

    //user view contact

    //user edit contact

    //user delete contact


}
