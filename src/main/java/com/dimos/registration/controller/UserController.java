package com.dimos.registration.controller;

import com.dimos.registration.entities.User;
import com.dimos.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String form(User user) {
        return "form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        userService.addUser(new User(user.getName(), user.getEmail()));
//        model.addAttribute("name", user.getName());
//        model.addAttribute("email", user.getEmail());
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "result";
    }

}
