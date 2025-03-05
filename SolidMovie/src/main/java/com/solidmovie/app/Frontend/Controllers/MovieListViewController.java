
package com.solidmovie.app.Frontend.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
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
                            // Create VBox layout
                            VBox vbox = new VBox();
                            vbox.setSpacing(5); // Space between elements
                            vbox.setStyle("-fx-padding: 10px; -fx-background-color: #444444;");

                            // Title (bold, yellow)
                            Label titleLabel = new Label(movie.getTitle());
                            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffd21b;");

                            // Description (normal, white)
                            Label descriptionLabel = new Label(movie.getDescription());
                            descriptionLabel.setWrapText(true);
                            descriptionLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12px;");

                            // Genres (italic, grey)
                            Label genreLabel = new Label(String.join(", ", movie.getGenres().toString()));
                            genreLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #cccccc; -fx-font-size: 12px;");

                            // Add elements to VBox
                            vbox.getChildren().addAll(titleLabel, descriptionLabel, genreLabel);

                            // Set VBox as the content of the cell
                            setGraphic(vbox);
                        }
                    }
                };
            }
        });
    }
}