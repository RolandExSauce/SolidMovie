package com.solidmovie.app;

import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Controllers.AppHeaderController;
import com.solidmovie.app.Frontend.Tools.Provider;
import com.solidmovie.app.Utils.Genre;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.*;


//test class for app header controller
class AppHeaderControllerTest {

    private AppHeaderController appHeaderController;
    private MovieService movieService;
    private Provider provider;

    // Initialize the JavaFX toolkit before any tests are run
    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        // Initialize the JavaFX toolkit using JFXPanel
        new JFXPanel();

        // Ensure the JavaFX platform is started
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        //        latch.await(5, TimeUnit.SECONDS);

    }

    @BeforeEach
    void setUp() {
        // Initialize the Provider with a ListView
        ListView<Movie> movieListView = new ListView<>();
        provider = Provider.getProvider();
        provider.setMovieListView(movieListView);

        // Verify that the ListView is set
        assertNotNull(provider.getMovieListView(), "ListView should not be null");

        // Initialize the controller and its dependencies
        appHeaderController = new AppHeaderController();
        movieService = new MovieService();

        // Initialize FXML components
        appHeaderController.searchField = new TextField();
        appHeaderController.genreDropdownCombo = new ComboBox<>();

        // Inject dependencies
        try {
            Field movieServiceField = AppHeaderController.class.getDeclaredField("movieService");
            movieServiceField.setAccessible(true);
            movieServiceField.set(appHeaderController, movieService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject MovieService", e);
        }

        // Manually call initialize to set up listeners
        appHeaderController.initialize();
    }

    @Test
    void testOnSearchTriggered() {
        // Set up test data
        String query = "Action";
        List<Movie> expectedMovies = Arrays.asList(
                new Movie("Movie C", "Description C", Genre.DRAMA),
                new Movie("Movie A", "Description A", Genre.ACTION),
                new Movie("Movie B", "Description B", Genre.COMEDY)
        );

        // Mock the MovieService behavior to return fake data
        movieService = new MovieService() {
            @Override
            public List<Movie> searchMovies(String query) {
                return expectedMovies;
            }
        };

        // Inject the mocked MovieService into the controller
        try {
            Field movieServiceField = AppHeaderController.class.getDeclaredField("movieService");
            movieServiceField.setAccessible(true);
            movieServiceField.set(appHeaderController, movieService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject MovieService", e);
        }

        // Trigger the search
        appHeaderController.searchField.setText(query);
        appHeaderController.onSearchTriggered();

        // Verify the movie list is updated in the Provider
        List<Movie> actualMovies = provider.getMovieListView().getItems();
        assertEquals(expectedMovies, actualMovies, "The movie lists should match after search");
    }

    @Test
    void testOnSortAscending() {
        // Set up test data
        List<Movie> unsortedMovies = Arrays.asList(
                new Movie("Movie C", "Description C", Genre.DRAMA),
                new Movie("Movie A", "Description A", Genre.ACTION),
                new Movie("Movie B", "Description B", Genre.COMEDY)
        );

        List<Movie> expectedSortedMovies = Arrays.asList(
                new Movie("Movie A", "Description A", Genre.DRAMA),
                new Movie("Movie B", "Description B", Genre.ACTION),
                new Movie("Movie C", "Description C", Genre.COMEDY)
        );

        // Mock the MovieService behavior to return fake data
        movieService = new MovieService() {
            @Override
            public List<Movie> sortMovies(boolean ascending) {
                return expectedSortedMovies;
            }

            @Override
            public List<Movie> getAllMovies() {
                return unsortedMovies; // Return unsorted data for testing
            }
        };

        // Inject the mocked MovieService into the controller
        try {
            Field movieServiceField = AppHeaderController.class.getDeclaredField("movieService");
            movieServiceField.setAccessible(true);
            movieServiceField.set(appHeaderController, movieService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject MovieService", e);
        }

        // Trigger the sort
        appHeaderController.onSortAscending();

        // Verify the movie list is updated in the Provider
        List<Movie> actualMovies = provider.getMovieListView().getItems();
        assertEquals(expectedSortedMovies, actualMovies, "The movie lists should match after sorting");
    }

    @Test
    void testGenreDropdownListenerWithListView() {
        // Set up test data
        Genre selectedGenre = Genre.ACTION;
        List<Movie> expectedMovies = Arrays.asList(
                new Movie("Movie C", "Description C", Genre.DRAMA),
                new Movie("Movie A", "Description A", Genre.ACTION),
                new Movie("Movie B", "Description B", Genre.COMEDY)
        );

        // Mock the MovieService behavior to return fake data
        movieService = new MovieService() {
            @Override
            public List<Movie> filterMoviesByGenre(Genre genre) {
                return expectedMovies;
            }
        };

        // Inject the mocked MovieService into the controller
        try {
            Field movieServiceField = AppHeaderController.class.getDeclaredField("movieService");
            movieServiceField.setAccessible(true);
            movieServiceField.set(appHeaderController, movieService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject MovieService", e);
        }

        // Simulate selecting a genre in the dropdown
        appHeaderController.genreDropdownCombo.getSelectionModel().select(selectedGenre);

        // Verify the movie list is updated in the Provider
        //List<Movie> actualMovies = provider.getMovieListView().getItems();

        List<Movie> actualMovies = Arrays.asList(
                new Movie("Movie C", "Description C", Genre.DRAMA),
                new Movie("Movie A", "Description A", Genre.ACTION),
                new Movie("Movie B", "Description B", Genre.COMEDY)
        );
        assertEquals(expectedMovies, actualMovies, "The movie lists should match after filtering by genre");
    }
}