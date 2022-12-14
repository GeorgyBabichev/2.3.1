package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping (value = "/delete")
    public String delete( @RequestParam (value = "id",required = false) Long id) {
        userService.removeUser(id);
        return "redirect:/" ;
    }


    @GetMapping("/editUser")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String update(@Valid User user) {
        userService.updateUser(user);
        return "redirect:/";

    }
}