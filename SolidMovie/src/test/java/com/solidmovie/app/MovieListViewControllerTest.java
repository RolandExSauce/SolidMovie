package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Controllers.MovieListViewController;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieListViewControllerTest {

    private MovieListViewController movieListViewController;

    @Mock
    private MovieService mockMovieService;

    @Mock
    private ListView<Movie> mockMovieListView;

    @BeforeEach
    void setUp() {
        movieListViewController = new MovieListViewController();

    }

    @Test
    void testInitialize_LoadsMoviesCorrectly() {
        List<Movie> mockMovies = List.of(
                new Movie("Inception", "Sci-Fi thriller", Genre.valueOf("SCIENCE_FICTION")),
                new Movie("The Dark Knight", "Action drama", Genre.valueOf("ACTION"))
        );

        when(mockMovieService.getAllMovies()).thenReturn(mockMovies);

        movieListViewController.initialize();

        // Verify if movie list is being set
        verify(mockMovieListView).getItems();
    }

    @Test
    void testGetMovieListView_NotNull() {
        assertNotNull(movieListViewController.getMovieListView(), "MovieListView should not be null");
    }

    @Test
    void testCustomCellFactory() {
        Callback<ListView<Movie>, ListCell<Movie>> factory = movieListViewController.getMovieListView().getCellFactory();
        assertNotNull(factory, "Custom cell factory should be set for movieListView");
    }
}
