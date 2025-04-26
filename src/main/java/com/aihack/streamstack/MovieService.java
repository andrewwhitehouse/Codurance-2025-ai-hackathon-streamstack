package com.aihack.streamstack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    public List<Movie> findAll() {
        return List.of(Movie.builder().title("movie1").description("first movie").build());
    }
}
