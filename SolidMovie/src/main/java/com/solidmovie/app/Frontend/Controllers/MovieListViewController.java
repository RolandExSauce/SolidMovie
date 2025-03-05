package com.solidmovie.app.Frontend.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import java.util.List;


//Movie list controller
public class MovieListViewController {

    @FXML private ListView<Movie> movieListView;

    public ListView<Movie> getMovieListView() { return movieListView; };

    private final MovieService movieService = new MovieService();

    // Constructor for dependency injection
//    public MovieListViewController(MovieService movieService) {
//        this.movieService = movieService;
//    };


    @FXML
    public void initialize() {

        //load movies from service
        List<Movie> movies = movieService.getAllMovies();

        movieListView.getItems().addAll(movies);

        // use custom list cell for every movie to render list
        movieListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Movie movie, boolean empty) {
                        super.updateItem(movie, empty);
                        if (empty || movie == null) {
                            setText(null);
                        } else {
                            setText(movie.getTitle() + " (" + movie.getGenres() + ")");
                        }
                    }
                };
            }
        });
    }
}
