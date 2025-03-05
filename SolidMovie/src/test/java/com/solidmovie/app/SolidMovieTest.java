package com.solidmovie.app;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@ExtendWith(ApplicationExtension.class)
class SolidMovieTest extends ApplicationTest {

    private Stage stage;

    @Start
    public void start(Stage stage) {

        this.stage = stage;
        SolidMovie solidMovie = new SolidMovie();
        try {
            solidMovie.start(stage);
        } catch (Exception e) {
            fail("Application failed to start: " + e.getMessage());
        }
    }

    @Test
    void testStageIsNotNull() {
        assertNotNull(stage, "Stage should not be null after application starts");
    }

    @Test
    void testSceneIsSet() {
        Scene scene = stage.getScene();
        assertNotNull(scene, "Scene should be set on the stage");
    }

    @Test
    void testFXMLLoadsSuccessfully() {
        assertDoesNotThrow(() -> new FXMLLoader(getClass().getResource("/com/solidmovie/app/fxml/main.fxml")).load(),
                "FXML file should load without exceptions");
    }

    @Test
    void testTransparentIconLoads() {
        String iconPath = "/com/solidmovie/app/static/icons/transparentIcon.png";
        try (InputStream iconStream = getClass().getResourceAsStream(iconPath)) {
            if (iconStream != null) {
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)));
                assertNotNull(icon, "Icon should load successfully");
            } else {
                System.out.println("Transparent icon not found!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
