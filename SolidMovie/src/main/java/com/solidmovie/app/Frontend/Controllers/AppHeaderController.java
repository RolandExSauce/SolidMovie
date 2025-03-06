package com.solidmovie.app.Frontend.Controllers;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Frontend.Tools.FrontendHelper;
import com.solidmovie.app.Frontend.Tools.Listeners;
import com.solidmovie.app.Frontend.Tools.Provider;
import com.solidmovie.app.Utils.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;


//controller to interact with buttons, combobox for filter, etc
public class AppHeaderController {

    private static final Provider provider = Provider.getProvider();

    @FXML
    public TextField searchField;

    @Getter
    @FXML public ComboBox<Genre> genreDropdownCombo;

    private final MovieService movieService = new MovieService();

    //init method
    @FXML
    public void initialize() {

        //initialize ComboBox
        FrontendHelper.initializeComboBox(genreDropdownCombo);

        //pass args to listener methods
        Listeners.addSearchTxtFieldListener(searchField,genreDropdownCombo, movieService, provider);
        Listeners.addComboGenreListener(searchField, genreDropdownCombo, movieService, provider);
    };
    /******************************************************************************************************************/
    @FXML
    public void onSortAscending() {
        FrontendHelper.updateMovieList(
                movieService.sortMovies(true), provider);
    };
}
