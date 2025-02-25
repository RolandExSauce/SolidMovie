package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Controllers.AppHeaderController;
import com.solidmovie.app.Frontend.Tools.Provider;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationTest;
import java.lang.reflect.Method;
import java.util.List;
import static org.mockito.Mockito.*;


//'extends ApplicationTest' is better when tests interacts with UI components
class AppHeaderControllerTest extends ApplicationTest {

    @Mock
    private MovieService movieService; // Mocked service

    @Mock
    private Provider provider; // Mocked provider

    @Mock
    private TextField searchField; // Mocked UI component

    @Mock
    private ComboBox<Genre> genreDropdownCombo; // Mocked UI component

    @InjectMocks
    private AppHeaderController controller; // Controller instance with injected mocks

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito annotations
    }

    @Test
    void testInitialize() {
        controller.initialize();

        // Verify ComboBox initialization
        verify(genreDropdownCombo, times(1)).getItems();

        // Verify event listeners are set
        verify(searchField, times(1)).setOnKeyPressed(any());
    }

    @Test
    void testOnSearchTriggered_Reflection() throws Exception {
        when(searchField.getText()).thenReturn("Inception");
        List<Movie> mockMovies = List.of(new Movie("Inception", "Sci-fi thriller", Genre.SCIENCE_FICTION));

        when(movieService.searchMovies("Inception")).thenReturn(mockMovies);

        // Use reflection to invoke private method
        Method method = AppHeaderController.class.getDeclaredMethod("onSearchTriggered");
        method.setAccessible(true); // Allows us to access private methods
        method.invoke(controller); // Calls the method

        verify(movieService, times(1)).searchMovies("Inception");
        verify(provider, never()).getMovieListView(); // No direct UI update calls
    }

    @Test
    void testOnSortAscending_Reflection() throws Exception {
        List<Movie> sortedMovies = List.of(new Movie("Avatar", "Sci-fi", Genre.SCIENCE_FICTION));
        when(movieService.sortMovies(true)).thenReturn(sortedMovies);

        // Use reflection to invoke private method
        Method method = AppHeaderController.class.getDeclaredMethod("onSortAscending");
        method.setAccessible(true);
        method.invoke(controller); // Calls the method

        verify(movieService, times(1)).sortMovies(true);
        verify(provider, never()).getMovieListView();
    }
}
