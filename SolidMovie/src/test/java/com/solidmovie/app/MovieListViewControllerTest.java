package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Controllers.MovieListViewController;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


//movie list view controller class
@ExtendWith(ApplicationExtension.class)
public class MovieListViewControllerTest {

    private MovieListViewController movieListViewController;

    @BeforeEach
    public void setUp() {
        // Create a test-specific subclass of MovieService
        MovieService movieService = new MovieService() {
            @Override
            public List<Movie> getAllMovies() {
                return Arrays.asList(
                        new Movie("Movie C", "Description C", Genre.DRAMA),
                        new Movie("Movie A", "Description A", Genre.ACTION),
                        new Movie("Movie B", "Description B", Genre.COMEDY)
                );
            }
        };

        // Initialize the controller with the test-specific MovieService
        movieListViewController = new MovieListViewController();

        // Use reflection to inject the MovieService into the controller
        try {
            Field movieServiceField = MovieListViewController.class.getDeclaredField("movieService");
            movieServiceField.setAccessible(true);
            movieServiceField.set(movieListViewController, movieService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject MovieService", e);
        }

        // Manually initialize the ListView
        try {
            // Use reflection to set the private movieListView field
            Field movieListViewField = MovieListViewController.class.getDeclaredField("movieListView");
            movieListViewField.setAccessible(true); // Allow access to the private field
            movieListViewField.set(movieListViewController, new ListView<>());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testInitialize() {
        // Act
        movieListViewController.initialize();

        // Assert
        List<Movie> actualMovies = movieListViewController.getMovieListView().getItems();
        assertEquals(3, actualMovies.size(), "The number of movies should match");
        assertEquals("Movie C", actualMovies.get(0).getTitle(), "First movie title should match");
        assertEquals("Movie A", actualMovies.get(1).getTitle(), "Second movie title should match");
        assertEquals("Movie B", actualMovies.get(2).getTitle(), "Third movie title should match");
    }
}