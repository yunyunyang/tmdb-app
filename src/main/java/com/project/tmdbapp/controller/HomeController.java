package com.project.tmdbapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("name", "World");
        return "index";
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public ModelAndView modelRenderPage() {
        Map<String, String> model = new HashMap<>();
        model.put("data", "Welcome to TMDB");
        ModelAndView mv = new ModelAndView();
//        view.setViewName("home");
//        view.getModel().putIfAbsent("welcome", "Hello World");
        return mv;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
