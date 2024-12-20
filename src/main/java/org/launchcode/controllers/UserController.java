package org.launchcode.controllers;

import jakarta.validation.Valid;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String displayAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        if (errors.hasErrors()) {
            return "user/add";
        }
        if (!user.getPassword().equals(verify)) {
            model.addAttribute("error", "Password do not match.");
            return "user/add";
        }
        return "user/index";
    }
}
