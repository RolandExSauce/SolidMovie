//package com.solidmovie.app;
//import com.solidmovie.app.Backend.Model.Movie;
//import com.solidmovie.app.Backend.Service.MovieService;
//import com.solidmovie.app.Utils.Genre;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import java.util.stream.Collectors;
//import static org.junit.jupiter.api.Assertions.*;
//
//
////test for movie service class
//class MovieServiceUnitTests {
//
//    private MovieService movieService;
//
//    // Centralized test data
//    private final List<Movie> movies = List.of(
//            new Movie("Movie A", "Description A", List.of(Genre.ACTION)),
//            new Movie("Movie B", "Description B", List.of(Genre.COMEDY)),
//            new Movie("Movie C", "Description C", List.of(Genre.DRAMA))
//    );
//
//    @Test
//    void testGetAllMovies() {
//        assertEquals(movies, movieService.getAllMovies(), "The movie list should match the test data");
//    };
//
//
//    @Test
//    void testSearchMovies() {
//        assertEquals(
//                List.of(movies.get(1)), // "Movie A"
//                movieService.searchMovies("Movie A"),
//                "Should find 1 movie by title"
//        );
//
//        assertTrue(movieService.searchMovies("nonexistent").isEmpty(),
//                "Should return an empty list for no matches");
//    };
//
//
//    @Test
//    void testSortMovies() {
//        List<Movie> ascendingExpected = movies.stream()
//                .sorted((m1, m2) -> m1.title().compareToIgnoreCase(m2.title()))
//                .collect(Collectors.toList());
//
//        List<Movie> descendingExpected = movies.stream()
//                .sorted((m1, m2) -> m2.title().compareToIgnoreCase(m1.title()))
//                .collect(Collectors.toList());
//
//        assertEquals(ascendingExpected, movieService.sortMovies(true),
//                "Movies should be sorted in ascending order");
//
//        assertEquals(descendingExpected, movieService.sortMovies(false),
//                "Movies should be sorted in descending order");
//    };
//
//
//    @Test
//    void testFilterMoviesByGenre() {
//        assertEquals(
//                List.of(movies.get(1)), // "Movie A" (ACTION)
//                movieService.filterMoviesByGenre(Genre.ACTION),
//                "Should find 1 movie with ACTION genre"
//        );
//    };
//}