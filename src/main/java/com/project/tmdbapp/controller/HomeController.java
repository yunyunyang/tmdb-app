package com.project.tmdbapp.controller;

import com.project.tmdbapp.enums.MovieEnums;
import com.project.tmdbapp.model.Movie;
import com.project.tmdbapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final MovieService movieService;

    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String index(Model model) {
        List<Movie> trendingMovie = movieService.fetchTrendingMovie();
        List<Movie> popularMovies = movieService.fetchPopularMovie();
        model.addAttribute("trendingMovie", trendingMovie);
        model.addAttribute("popularMovies", popularMovies);
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
