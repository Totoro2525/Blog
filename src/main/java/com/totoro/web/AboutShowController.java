package com.totoro.web;

import com.totoro.po.User;
import com.totoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutShowController {

    @Autowired
    private UserService userService;

    @GetMapping("/about")
    public String about(Model model) {
        User me = userService.me();
        model.addAttribute("avatar", me.getAvatar());
        return "about";
    }
}
