package com.solidmovie.app.Frontend.Tools;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.stream.Collectors;


//listener class
public class Listeners {

    //search text field listener
    public static void addSearchTxtFieldListener(
            TextField searchField,
            ComboBox<Genre> genreDropdown,
            MovieService movieService,
            Provider provider
    ) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.trim();
            Genre selectedGenre = genreDropdown.getValue();

            List<Movie> filteredMovies;

            if (query.isEmpty()) {
                // If the search field is empty but a genre is selected, filter by genre only
                if (selectedGenre != null && selectedGenre != Genre.NONE) {
                    filteredMovies = movieService.filterMoviesByGenre(selectedGenre);
                } else {
                    // If no genre is selected, show all movies
                    filteredMovies = movieService.getAllMovies();
                }
            } else {
                // If there is a search query, apply both genre and search filters
                filteredMovies = movieService.getAllMovies().stream()
                        .filter(movie -> movie.title().toLowerCase().contains(query.toLowerCase())
                                || movie.description().toLowerCase().contains(query.toLowerCase()))
                        .collect(Collectors.toList());

                if (selectedGenre != null && selectedGenre != Genre.NONE) {
                    // Further filter by genre if a genre is selected
                    filteredMovies = filteredMovies.stream()
                            .filter(movie -> movie.genres().contains(selectedGenre))
                            .collect(Collectors.toList());
                }
            }

            // Update the movie list with the filtered results
            FrontendHelper.updateMovieList(filteredMovies, provider);
        });
    };
    /******************************************************************************************************************/
    public static void addComboGenreListener(
            TextField searchField,
            ComboBox<Genre> genreDropdown,
            MovieService movieService,
            Provider provider
    ) {
        genreDropdown.valueProperty().addListener((
                observable, oldValue, newValue) -> {
            List<Movie> filteredMovies;

            // If no genre is selected, fallback to all movies
            if (newValue == Genre.NONE) {
                filteredMovies = movieService.getAllMovies();
            } else {
                filteredMovies = movieService.filterMoviesByGenre(newValue);
            }

            // Apply search query filtering **only if there is text**
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                String lowerCaseQuery = query.toLowerCase();
                filteredMovies = filteredMovies.stream()
                        .filter(movie -> movie.title().toLowerCase().contains(lowerCaseQuery)
                                || movie.description().toLowerCase().contains(lowerCaseQuery))
                        .collect(Collectors.toList());
            }


            // Update UI with the final filtered list
            FrontendHelper.updateMovieList(filteredMovies, provider);
        });
    }

}
