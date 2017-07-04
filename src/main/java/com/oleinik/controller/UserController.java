package com.oleinik.controller;

import com.oleinik.entity.User;
import com.oleinik.service.SecurityService;
import com.oleinik.service.UserService;
import com.oleinik.util.JsonStatus;
import com.oleinik.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.create(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/myset", method = RequestMethod.GET)
    public String editUserSet(){
        return "user_set";
    }

    @RequestMapping(value = "/setsize", method = RequestMethod.GET)
    @ResponseBody public JsonStatus userSetSize(Principal principal) {
        if (principal == null) return new JsonStatus(JsonStatus.ERROR);
        User user = userService.findByUsername(principal.getName());
        if (user.getLangUnits() == null) return new JsonStatus(JsonStatus.OK, "0");
        return new JsonStatus(JsonStatus.OK, String.valueOf( user.getLangUnits().size() ));
    }


}
