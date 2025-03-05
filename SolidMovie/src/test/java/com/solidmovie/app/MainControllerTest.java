package com.solidmovie.app;
import com.solidmovie.app.Frontend.Controllers.MainController;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class MainControllerTest {

    private MainController mainController;
    private VBox headerContainer;
    private VBox movielistContainer;

    @Start
    public void start(Stage stage) throws Exception {
        // Initialize the JavaFX environment
        headerContainer = new VBox();
        movielistContainer = new VBox();

        // Create an instance of MainController
        mainController = new MainController();

        // Use reflection to set private fields (headerContainer and movielistContainer)
        setPrivateField(mainController, "headerContainer", headerContainer);
        setPrivateField(mainController, "movielistContainer", movielistContainer);
    }

    @BeforeEach
    public void setUp() {
        // Reset the containers before each test
        headerContainer.getChildren().clear();
        movielistContainer.getChildren().clear();
    }

    @Test
    public void testInitialize() {
        // Call the initialize method
        mainController.initialize();

        // Verify that the containers have children after initialization
        assertFalse(headerContainer.getChildren().isEmpty(), "Header container should not be empty after initialization");
        assertFalse(movielistContainer.getChildren().isEmpty(), "Movie list container should not be empty after initialization");

        // Verify that the children are instances of Node
        assertInstanceOf(Node.class, headerContainer.getChildren().getFirst(), "Header container should contain a Node");
        assertInstanceOf(Node.class, movielistContainer.getChildren().getFirst(), "Movie list container should contain a Node");
    }

    @Test
    public void testLoadIncludedFXML() throws Exception {
        // Use reflection to access the private loadIncludedFXML method
        Method loadIncludedFXMLMethod = MainController.class.getDeclaredMethod("loadIncludedFXML", String.class, VBox.class);
        loadIncludedFXMLMethod.setAccessible(true); // Make the private method accessible

        // Invoke the private method to load the header FXML
        loadIncludedFXMLMethod.invoke(mainController, "/com/solidmovie/app/fxml/app-header.fxml", headerContainer);

        // Verify that the node was added to the container
        assertFalse(headerContainer.getChildren().isEmpty(), "Header container should not be empty after loading FXML");
        assertNotNull(headerContainer.getChildren().getFirst(), "Header container should contain a Node");

        // Invoke the private method to load the movie list FXML
        loadIncludedFXMLMethod.invoke(mainController, "/com/solidmovie/app/fxml/movielist-view.fxml", movielistContainer);

        // Verify that the node was added to the container
        assertFalse(movielistContainer.getChildren().isEmpty(), "Movie list container should not be empty after loading FXML");
        assertInstanceOf(Node.class, movielistContainer.getChildren().getFirst(), "Movie list container should contain a Node");
    }

    // Helper method to set private fields using reflection
    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true); // Make the private field accessible
        field.set(target, value); // Set the value
    }
}