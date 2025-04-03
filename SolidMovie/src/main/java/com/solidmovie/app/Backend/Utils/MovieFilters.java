package com.solidmovie.app.Backend.Utils;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Utils.Genre;

import java.util.function.Predicate;


//movie filters method
public class MovieFilters {
    public static Predicate<Movie> byGenre(Genre genre) {
        return genre == null ? movie -> true : movie -> movie.genres().contains(genre);
    }

    public static Predicate<Movie> byRating(Double minRating) {
        return minRating == null ? movie -> true : movie -> movie.rating() >= minRating;
    }

    public static Predicate<Movie> byReleaseYear(Integer year) {
        return year == null ? movie -> true : movie -> movie.releaseYear() == year;
    }
};