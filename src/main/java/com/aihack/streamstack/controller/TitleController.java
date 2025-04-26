package com.aihack.streamstack.controller;

import com.aihack.streamstack.model.Title;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    private final List<Title> titles = new ArrayList<>();

    public TitleController() {
        // Initialize with some sample data
        titles.add(new Title("Stranger Things", "Series", 2016, "The Duffer Brothers", "TV-14"));
        titles.add(new Title("The Crown", "Series", 2016, "Peter Morgan", "TV-MA"));
        titles.add(new Title("Bird Box", "Movie", 2018, "Susanne Bier", "R"));
        titles.add(new Title("The Irishman", "Movie", 2019, "Martin Scorsese", "R"));
    }

    @GetMapping
    public List<Title> getAllTitles(
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String sortBy) {
        
        List<Title> filteredTitles = titles;

        // Apply filters
        if (director != null) {
            filteredTitles = filteredTitles.stream()
                    .filter(title -> title.getDirector().equalsIgnoreCase(director))
                    .collect(Collectors.toList());
        }

        if (rating != null) {
            filteredTitles = filteredTitles.stream()
                    .filter(title -> title.getRating().equalsIgnoreCase(rating))
                    .collect(Collectors.toList());
        }

        // Apply sorting
        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "year":
                    filteredTitles.sort(Comparator.comparingInt(Title::getReleaseYear));
                    break;
                case "title":
                    filteredTitles.sort(Comparator.comparing(Title::getTitle));
                    break;
            }
        }

        return filteredTitles;
    }

    @PostMapping
    public Title addTitle(@RequestBody Title title) {
        titles.add(title);
        return title;
    }
} 