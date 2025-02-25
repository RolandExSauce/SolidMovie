package com.solidmovie.app.Frontend.Tools;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.List;


//listener class
public class Listeners {

    //search text field listener
    public static void addSearchTxtFieldListener(
            TextField searchField,
            MovieService movieService,
            Provider provider
    ) {
        // **Add a listener to reset movie list when search is cleared**
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                Helpers.updateMovieList(movieService.getAllMovies(), provider);
            }
        });
    };
    /******************************************************************************************************************/
    public static void addComboGenreListener(
            ComboBox<Genre> genreDropdown,
            MovieService movieService,
            Provider provider
    ) {
        // Add event listener to the ComboBox to update movies when genre changes
        genreDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Genre.NONE) {
                // reset to show all movies when "Filter by Genre" is selected
                Helpers.updateMovieList(movieService.getAllMovies(), provider);
            } else {
                // Otherwise, filter movies by the selected genre
                List<Movie> filteredMovies = movieService.filterMoviesByGenre(newValue);
                Helpers.updateMovieList(filteredMovies, provider);
            }
        });
    };
}
