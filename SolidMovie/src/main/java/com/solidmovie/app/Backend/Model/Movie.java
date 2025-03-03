package com.solidmovie.app.Backend.Model;
import com.solidmovie.app.Utils.Genre;
import java.util.List;


//Define "Model", in this case the structure of a movie, later we
//can add here entity classes in combination with mappers such as hibernate or whatever
public class Movie  {
    private final String title;
    private final String description;
    private final List<Genre> genres;

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public List<Genre> getGenres() { return genres; }

    //maybe should not use varargs 🤔 ? that means a Movie could be created without a genre, for now its fine I guess
    public Movie(String title, String description, Genre... genres) {
        this.title = title;
        this.description = description;
        this.genres = List.of(genres); // convert varargs to an immutable list
    };
}


