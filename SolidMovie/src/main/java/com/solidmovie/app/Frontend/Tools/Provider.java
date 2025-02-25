package com.solidmovie.app.Frontend.Tools;
import javafx.scene.control.ListView;
import com.solidmovie.app.Backend.Model.Movie;


//provider singleton class, though for now we could access movieListView directly from MovieListViewController
public class Provider {

    private static final Provider instance = new Provider();

    public ListView<Movie> getMovieListView() {
        return movieListView;
    };

    public void setMovieListView(ListView<Movie> movieListView) {
        this.movieListView = movieListView;
    };

    private ListView<Movie> movieListView; // store reference

    public static Provider getProvider() {
        return instance;
    };
}
