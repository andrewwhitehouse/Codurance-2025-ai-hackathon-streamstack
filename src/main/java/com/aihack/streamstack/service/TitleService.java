package com.aihack.streamstack.service;

import com.aihack.streamstack.model.Title;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitleService {
    private final List<Title> titles = new ArrayList<>();

    public TitleService() {
        loadTitlesFromCSV();
    }

    private void loadTitlesFromCSV() {
        try {
            ClassPathResource resource = new ClassPathResource("netflix-titles.csv");
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                
                for (CSVRecord record : csvParser) {
                    String title = record.get("title");
                    String type = record.get("type");
                    int releaseYear = Integer.parseInt(record.get("release_year"));
                    String director = record.get("director");
                    String rating = record.get("rating");
                    
                    titles.add(new Title(title, type, releaseYear, director, rating));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading Netflix titles from CSV", e);
        }
    }

    public List<Title> getAllTitles() {
        return new ArrayList<>(titles);
    }

    public List<Title> getTitlesByDirector(String director) {
        return titles.stream()
                .filter(title -> title.getDirector().equalsIgnoreCase(director))
                .toList();
    }

    public List<Title> getTitlesByRating(String rating) {
        return titles.stream()
                .filter(title -> title.getRating().equalsIgnoreCase(rating))
                .toList();
    }

    public List<Title> getTitlesSortedByYear() {
        return titles.stream()
                .sorted((t1, t2) -> Integer.compare(t2.getReleaseYear(), t1.getReleaseYear()))
                .toList();
    }

    public List<Title> getTitlesSortedByTitle() {
        return titles.stream()
                .sorted((t1, t2) -> t1.getTitle().compareToIgnoreCase(t2.getTitle()))
                .toList();
    }
} 