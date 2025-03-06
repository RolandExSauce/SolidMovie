package com.solidmovie.app.Frontend.Tools;
import com.solidmovie.app.Utils.Genre;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import com.solidmovie.app.Backend.Model.Movie;
import lombok.Getter;
import lombok.Setter;


//provider singleton class, though for now we could access
//movieListView directly from MovieListViewController
public class Provider {

    private Provider (){ }; //prevent instantiation

    private static final Provider instance = new Provider();

    @Getter
    @Setter
    private ListView<Movie> movieListView;

    @Getter
    @Setter
    public ComboBox<Genre> genreDropdownCombo;

    public static Provider getProvider() { return instance; }
};