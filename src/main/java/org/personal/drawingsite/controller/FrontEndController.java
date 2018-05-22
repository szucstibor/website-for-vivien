package org.personal.drawingsite.controller;

import org.personal.drawingsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class FrontEndController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/references")
    public String references(Model model) {
        return "references";
    }

    @GetMapping("/active-requests")
    public String activeRequests(Model model) {
        return "active";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam Map<String, String> reqPar) {
        System.out.println(reqPar.size());
        userService.register(reqPar);
        return "redirect:/";
    }
}
