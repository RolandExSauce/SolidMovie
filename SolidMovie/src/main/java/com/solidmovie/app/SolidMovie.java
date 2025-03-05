package com.solidmovie.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

//Entry class
public class SolidMovie extends Application {
    private static final String mainXML = "/com/solidmovie/app/fxml/main.fxml";
    private static final String transparentIcon = "/com/solidmovie/app/static/icons/transparentIcon.png";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(mainXML));
            // Make stage undecorated so that we can style
            Scene scene = new Scene(fxmlLoader.load(), 1300, 800);
            stage.setScene(scene);

            //Load a transparent or empty icon
            try (InputStream iconStream = getClass().getResourceAsStream(transparentIcon)) {
                if (iconStream != null) {
                    stage.getIcons().clear(); // Remove all previous icons
                    stage.getIcons().add(new Image(Objects.requireNonNull
                            (getClass().getResourceAsStream(transparentIcon))));
                } else System.out.println("Transparent icon not found!");
            };
            stage.show();
        } catch (IOException | RuntimeException ex) {
            System.out.println("Error: " + ex.getMessage());
        };
    }

    //start main method and call launch method
    public static void main(String[] args) {
        launch(args);
    };
}