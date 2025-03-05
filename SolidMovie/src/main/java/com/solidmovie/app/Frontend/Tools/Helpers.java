package com.solidmovie.app.Frontend.Tools;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import java.util.List;


//helper class to extract methods here
public class Helpers {

    //update the movie list
    public static void updateMovieList(List<Movie> movies, Provider provider) {
        provider.getMovieListView().getItems().clear();
        for (Movie movie : movies) {
            provider.getMovieListView().getItems().add(movie);
        };
    };


    // Initialize the genre dropdown ComboBox for AppHeaderController
    public static void initializeComboBox(ComboBox<Genre> genreDropdownCombo) {
        genreDropdownCombo.getItems().addAll(Genre.values());
        genreDropdownCombo.getSelectionModel().select(Genre.NONE); // Set default selection

        // Set the default display text when no item is selected
        genreDropdownCombo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty) ? Genre.NONE.toString() : item.toString());
            }
        });
    }
}
