package com.solidmovie.app.Frontend.Tools;
import javafx.scene.control.ListView;
import com.solidmovie.app.Backend.Model.Movie;


//provider singleton class, though for now we could access movieListView directly from MovieListViewController
public class Provider {
    private static final Provider instance = new Provider();
    private ListView<Movie> movieListView;

    public ListView<Movie> getMovieListView() {
        if (movieListView == null) {
            System.err.println("Warning: movieListView is null!");
        }
        return movieListView;
    }

    public void setMovieListView(ListView<Movie> movieListView) {
        if (movieListView == null) {
            throw new IllegalArgumentException("movieListView cannot be null");
        }
        this.movieListView = movieListView;
    }

    public static Provider getProvider() {
        return instance;
    }
}