package com.aihack.streamstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Scanner;

@Controller
public class StreamstackController {
    private final MovieService movieService;

    public StreamstackController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        List<Movie> movies = movieService.findAll(); // Assume movieService is a service to fetch movies
        model.addAttribute("movies", movies);
        return "catalogue";
    }
}
