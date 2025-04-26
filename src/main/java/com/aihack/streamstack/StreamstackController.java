package com.aihack.streamstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StreamstackController {
    private final MovieService movieService;

    public StreamstackController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) throws IOException {
        List<Movie> movies = movieService.findAll(); // Assume movieService is a service to fetch movies
        model.addAttribute("movies", movies);
        return "catalogue";
    }

    @GetMapping("/catalogue/{id}")
    public String catalogueDetail(@PathVariable String id, Model model) throws IOException {
        Optional<Movie> movie = movieService.findById(id);
        model.addAttribute("movieDetails", movie.get());
        return "catalogueDetail";
    }
}
