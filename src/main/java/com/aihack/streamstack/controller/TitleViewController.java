package com.aihack.streamstack.controller;

import com.aihack.streamstack.model.Title;
import com.aihack.streamstack.service.TitleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/titles")
public class TitleViewController {

    private final TitleService titleService;

    public TitleViewController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping
    public String viewTitles(
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String sortBy,
            Model model) {
        
        List<Title> titles;
        
        if (director != null) {
            titles = titleService.getTitlesByDirector(director);
        } else if (rating != null) {
            titles = titleService.getTitlesByRating(rating);
        } else if ("year".equals(sortBy)) {
            titles = titleService.getTitlesSortedByYear();
        } else if ("title".equals(sortBy)) {
            titles = titleService.getTitlesSortedByTitle();
        } else {
            titles = titleService.getAllTitles();
        }

        model.addAttribute("titles", titles);
        return "titles";
    }
} 