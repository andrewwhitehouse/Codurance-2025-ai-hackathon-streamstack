package com.aihack.streamstack.model;

public class Title {
    private String title;
    private String type;
    private int releaseYear;
    private String director;
    private String rating;

    public Title(String title, String type, int releaseYear, String director, String rating) {
        this.title = title;
        this.type = type;
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
} 