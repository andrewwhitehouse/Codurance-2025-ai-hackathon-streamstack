package com.aihack.streamstack;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.csv.CSVFormat.RFC4180;

@Service
public class MovieService {
    private final ResourceLoader resourceLoader;
    private List<Movie> cachedMovies = new ArrayList<>();

    public MovieService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Movie> findMovies() throws IOException {
        if (cachedMovies.isEmpty()) {
            Resource resource = resourceLoader.getResource("classpath:netflix_titles.csv");

            File file = resource.getFile();
            CSVParser parser = CSVParser.parse(file, UTF_8, RFC4180);
            List<Movie> results = new ArrayList<>();
            for(CSVRecord record : parser) {
                String id = record.get(0);
                String title = record.get(2);
                String description = record.get(record.size()-1);
                results.add(Movie.builder().description(description).title(title).id(id).build());
            }
            results.remove(0);
            cachedMovies = results;
        }
        return cachedMovies;
    }

    public List<Movie> findAll() throws IOException {
        return findMovies();
    }

    public Optional<Movie> findById(String id) throws IOException {
        return findMovies().stream().filter(m -> m.id.equals(id)).findFirst();
    }
}
