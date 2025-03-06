package com.solidmovie.app.Frontend.Controllers;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Model.MovieAPI;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Tools.Helpers;
import com.solidmovie.app.Frontend.Tools.Listeners;
import com.solidmovie.app.Frontend.Tools.Provider;
import com.solidmovie.app.Utils.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import java.util.List;


//controller to interact with buttons, combobox for filter, etc
public class AppHeaderController {

    private static final Provider provider = Provider.getProvider();

    @FXML
    public TextField searchField;

    @FXML public ComboBox<Genre> genreDropdownCombo;

    private final MovieService movieService = new MovieService();

    //init method
    @FXML
    public void initialize() {

        //initialize ComboBox
        Helpers.initializeComboBox(genreDropdownCombo);

        // Add event handler to searchField for Enter key press
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onSearchTriggered();
            }
        });

        //pass args to listener methods
        Listeners.addSearchTxtFieldListener(searchField, movieService, provider);
        Listeners.addComboGenreListener(genreDropdownCombo, movieService, provider);
    };
    /******************************************************************************************************************/
    //search will be triggered on enter click
    public void onSearchTriggered() {
        String query = searchField.getText();
        List<Movie> filteredMovies = movieService.searchMovies(query);
        Helpers.updateMovieList(filteredMovies, provider);
    };
    /******************************************************************************************************************/
    //sort alphabetically
    @FXML
    public void onSortAscending() {

        //System.out.println("Request sent to backend");
       new MovieAPI().sendRequest();



//        Helpers.updateMovieList(
//                movieService.sortMovies(true), provider);
    };
}