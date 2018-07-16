package org.personal.drawingsite.controller;

import org.personal.drawingsite.drawRequest.DrawRequest;
import org.personal.drawingsite.drawRequest.DrawRequestService;
import org.personal.drawingsite.image.Image;
import org.personal.drawingsite.image.ImageRepository;
import org.personal.drawingsite.user.User;
import org.personal.drawingsite.user.UserRegistrationDto;
import org.personal.drawingsite.user.UserRepository;
import org.personal.drawingsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Controller
public class FrontEndController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DrawRequestService drawRequestService;

    @Autowired
    ImageRepository imageRepository;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/references")
    public String references(Model model) {
        List<Image> images = imageRepository.findAll();
        List<DrawRequest> allRequests = drawRequestService.getAllAcceptedRequests();
        DrawRequest active = drawRequestService.getActiveRequest();
        model.addAttribute("allRequests", allRequests);
        model.addAttribute("active", active);
        model.addAttribute("images", images);
        return "references";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") UserRegistrationDto userDto,
                           BindingResult result) {
        User existingName = userService.findByUserName(userDto.getUserName());
        User existingEmail = userService.findByEmail(userDto.getEmail());
        if (existingName != null || existingEmail != null){
            result.rejectValue("Existing user", null, "There is already an account " +
                    "registered with that username or email");
        }

        if (result.hasErrors()){
            return result.getAllErrors().toString();
        }

        userService.register(userDto);
        return "redirect:/";
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String request(@RequestParam Map<String, String> reqPar){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        reqPar.put("username", username);
        drawRequestService.saveRequest(reqPar);
        return "redirect:/";
    }

    @RequestMapping(value = "/accept-requests")
    public String allRequests(Model model){
        List<DrawRequest> allAcceptedRequests = drawRequestService.getAllAcceptedRequests();
        List<DrawRequest> allNotAcceptedRequests = drawRequestService.getAllNotAcceptedRequests();
        DrawRequest active = drawRequestService.getActiveRequest();
        int maxAmount = Math.max(allAcceptedRequests.size(), allNotAcceptedRequests.size());
        if (active != null) {
            model.addAttribute("active", active);
        }
        model.addAttribute("accepteds", allAcceptedRequests);
        model.addAttribute("notAccepteds", allNotAcceptedRequests);
        model.addAttribute("rows", maxAmount);
        return "all_request";
    }

    @RequestMapping(value = "/accept/{requestId}")
    public String acceptRequest(@PathVariable String requestId){
        drawRequestService.setAcceptedToTrue(Long.parseLong(requestId));
        return "redirect:/accept-requests";
    }

    @RequestMapping(value = "/focus/{requestId}")
    public String focusOnAccepted(@PathVariable String requestId){
        drawRequestService.focusOnRequest(Long.parseLong(requestId));
        return "redirect:/accept-requests";
    }

    @RequestMapping(value = "/done")
    public String finishedRequest(){
        drawRequestService.removeCurrentlyActive();
        return "redirect:/accept-requests";
    }

    @RequestMapping(value = "/decline/{requestId}")
    public String decline(@PathVariable String requestId){
        drawRequestService.removeRequest(Long.parseLong(requestId));
        return "redirect:/accept-requests";
    }

    @RequestMapping(value = "/reset-db")
    public String resetDb(){
        drawRequestService.resetAll();
        return "redirect:/accept-requests";
    }
}
