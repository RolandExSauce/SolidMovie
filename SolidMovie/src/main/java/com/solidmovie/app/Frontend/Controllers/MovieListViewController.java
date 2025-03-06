package com.solidmovie.app.Frontend.Controllers;
import com.solidmovie.app.Utils.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import lombok.Getter;
import java.util.List;


//Movie list controller
public class MovieListViewController {

    @Getter
    @FXML private ListView<Movie> movieListView;;

    private final MovieService movieService = new MovieService();

    @FXML
    public void initialize() {
        // Load movies from service
        List<Movie> movies = movieService.getAllMovies();
        movieListView.getItems().addAll(movies);

        // Use custom ListCell for each movie to render list
        movieListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Movie movie, boolean empty) {
                        super.updateItem(movie, empty);
                        if (empty || movie == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Load FXML for each cell
                            VBox movieCell = new VBox();
                            movieCell.getStyleClass().add("movie-cell");

                            Label titleLabel = new Label(movie.title());
                            titleLabel.getStyleClass().add("movie-title");

                            Label descriptionLabel = new Label(movie.description());
                            descriptionLabel.getStyleClass().add("movie-description");
                            descriptionLabel.setWrapText(true);

                            Label genreLabel = new Label(String.join(", ", movie.genres().stream()
                                    .map(Genre::toString) // convert each Genre to a String
                                    .toList())); // collect as a List<String>

                            genreLabel.getStyleClass().add("movie-genre");

                            movieCell.getChildren().addAll(titleLabel, descriptionLabel, genreLabel);
                            setGraphic(movieCell);

                        }
                    }
                };
            }
        });
    }
}