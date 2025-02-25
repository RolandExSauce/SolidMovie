package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Repo.MovieRepo;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Utils.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private MovieRepo mockMovieRepo;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
    }

    @Test
    void testGetAllMovies() {
        List<Movie> mockMovies = List.of(
                new Movie("Inception", "Sci-Fi thriller", Genre.SCIENCE_FICTION),
                new Movie("The Dark Knight", "Action drama", Genre.ACTION)
        );

        when(mockMovieRepo.getAllMovies()).thenReturn(mockMovies);

        List<Movie> movies = movieService.getAllMovies();

        assertNotNull(movies, "Movie list should not be null");
        assertFalse(movies.isEmpty(), "Movie list should not be empty");
        assertEquals(2, movies.size(), "Movie list should contain exactly 2 movies");
    }

    @Test
    void testSearchMovies() {
        List<Movie> mockMovies = List.of(
                new Movie("Inception", "Sci-Fi thriller", Genre.SCIENCE_FICTION),
                new Movie("The Dark Knight", "Action drama", Genre.ACTION)
        );

        when(mockMovieRepo.getAllMovies()).thenReturn(mockMovies);

        List<Movie> searchResults = movieService.searchMovies("Inception");

        assertNotNull(searchResults);
        assertEquals(1, searchResults.size(), "Search should return one movie");
        assertEquals("Inception", searchResults.get(0).getTitle());
    }

    @Test
    void testSearchMovies_NoResults() {
        when(mockMovieRepo.getAllMovies()).thenReturn(List.of());

        List<Movie> searchResults = movieService.searchMovies("Nonexistent Movie");

        assertNotNull(searchResults);
        assertTrue(searchResults.isEmpty(), "Search should return an empty list for non-matching query");
    }

    @Test
    void testSortMoviesAscending() {
        List<Movie> mockMovies = List.of(
                new Movie("Zodiac", "Crime thriller", Genre.CRIME),
                new Movie("Inception", "Sci-Fi thriller", Genre.SCIENCE_FICTION),
                new Movie("Batman Begins", "Action drama", Genre.ACTION)
        );

        when(mockMovieRepo.getAllMovies()).thenReturn(mockMovies);

        List<Movie> sortedMovies = movieService.sortMovies(true);

        assertNotNull(sortedMovies);
        assertEquals("Batman Begins", sortedMovies.get(0).getTitle());
        assertEquals("Inception", sortedMovies.get(1).getTitle());
        assertEquals("Zodiac", sortedMovies.get(2).getTitle());
    }

    @Test
    void testSortMoviesDescending() {
        List<Movie> mockMovies = List.of(
                new Movie("Zodiac", "Crime thriller", Genre.CRIME),
                new Movie("Inception", "Sci-Fi thriller", Genre.SCIENCE_FICTION),
                new Movie("Batman Begins", "Action drama", Genre.ACTION)
        );

        when(mockMovieRepo.getAllMovies()).thenReturn(mockMovies);

        List<Movie> sortedMovies = movieService.sortMovies(false);

        assertNotNull(sortedMovies);
        assertEquals("Zodiac", sortedMovies.get(0).getTitle());
        assertEquals("Inception", sortedMovies.get(1).getTitle());
        assertEquals("Batman Begins", sortedMovies.get(2).getTitle());
    }

    @Test
    void testFilterMoviesByGenre() {
        List<Movie> mockMovies = List.of(
                new Movie("Inception", "Sci-Fi thriller", Genre.SCIENCE_FICTION),
                new Movie("The Dark Knight", "Action drama", Genre.ACTION),
                new Movie("Interstellar", "Sci-Fi space drama", Genre.SCIENCE_FICTION)
        );

        when(mockMovieRepo.getAllMovies()).thenReturn(mockMovies);

        List<Movie> filteredMovies = movieService.filterMoviesByGenre(Genre.SCIENCE_FICTION);

        assertNotNull(filteredMovies);
        assertEquals(2, filteredMovies.size(), "Filtering by SCIENCE_FICTION should return 2 movies");
        assertTrue(filteredMovies.stream().allMatch(movie -> movie.getGenres().contains(Genre.SCIENCE_FICTION)));
    }

    @Test
    void testFilterMoviesByGenre_NoResults() {
        when(mockMovieRepo.getAllMovies()).thenReturn(List.of());

        List<Movie> filteredMovies = movieService.filterMoviesByGenre(Genre.HORROR);

        assertNotNull(filteredMovies);
        assertTrue(filteredMovies.isEmpty(), "Filtering by HORROR should return an empty list");
    }
}
