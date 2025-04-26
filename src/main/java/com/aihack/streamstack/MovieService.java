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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.csv.CSVFormat.RFC4180;

@Service
public class MovieService {
    private final ResourceLoader resourceLoader;

    public MovieService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Movie> findAll() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:netflix_titles.csv");

        File file = resource.getFile();
        CSVParser parser = CSVParser.parse(file, UTF_8, RFC4180);
        List<Movie> results = new ArrayList<>();
        for(CSVRecord record : parser) {
            String title = record.get(2);
            String description = record.get(3);
            results.add(Movie.builder().description(description).title(title).build());
        }
        results.remove(0);

        return results;
    }
}
