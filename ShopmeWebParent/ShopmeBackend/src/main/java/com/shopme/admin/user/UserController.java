package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listAll(Model model) {
	List<User> listUsers = userService.listAll();
	model.addAttribute("listUsers", listUsers);
	return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
	User user = new User();
	user.setEnabled(true);
	List<Role> listRoles = userService.listRoles();
	model.addAttribute("listRoles", listRoles);
	model.addAttribute("user", user);
	return "user_form";

    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes attributes) {
	System.out.println(user);
	userService.save(user);
	attributes.addFlashAttribute("message", "User has been saved successfully");
	return "redirect:/users";

    }

}
