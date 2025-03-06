package com.solidmovie.app.Frontend.Controllers;
import com.solidmovie.app.Frontend.Tools.Provider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import java.io.IOException;

// Main controller to load other controllers
public class MainController {

    private static final Provider provider = Provider.getProvider();
    private static final String appHeaderPathFXML = "/com/solidmovie/app/fxml/app-header.fxml";
    private static final String movieListViewPathFXML = "/com/solidmovie/app/fxml/movielist-view.fxml";

    @FXML
    private VBox headerContainer, movielistContainer; // Containers for header and movie list

    @FXML
    public void initialize() {
        loadIncludedFXML(appHeaderPathFXML, headerContainer);
        loadIncludedFXML(movieListViewPathFXML, movielistContainer);
    };

    private void loadIncludedFXML(String fxmlPath, VBox targetContainer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            Node loadedNode = loader.load(); //load specified fxml file and get root node
            targetContainer.getChildren().add(loadedNode); //add loaded node to target VBOX
            Object c = loader.getController();

            if (c instanceof MovieListViewController movieListViewController) {
                provider.setMovieListView(movieListViewController.getMovieListView());
            };

            if((c instanceof AppHeaderController appHeaderController) ){
                provider.setGenreDropdownCombo(appHeaderController.getGenreDropdownCombo());
            };

        } catch (IOException e) {
            System.err.println("Error loading FXML: " + fxmlPath + " - " + e.getMessage());
        }
    }
}