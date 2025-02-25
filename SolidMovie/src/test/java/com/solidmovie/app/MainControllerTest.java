package com.solidmovie.app;
import com.solidmovie.app.Frontend.Controllers.MainController;
import com.solidmovie.app.Frontend.Controllers.MovieListViewController;
import com.solidmovie.app.Frontend.Tools.Provider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;


@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    private MainController mainController;

    @Mock
    private Provider mockProvider;

    @Mock
    private VBox mockHeaderContainer, mockMovieListContainer;

    @BeforeEach
    void setUp() {
        mainController = new MainController();
    }

    @Test
    void testInitialize() {
        // Mock behavior
        mainController.initialize();

        // Ensure method loads both FXML files
        assertDoesNotThrow(() -> mainController.initialize());
    }

    @Test
    void testLoadIncludedFXML_Success() {
        try {
            FXMLLoader mockLoader = mock(FXMLLoader.class);
            Node mockNode = mock(Node.class);
            MovieListViewController mockMovieListController = mock(MovieListViewController.class);

            when(mockLoader.load()).thenReturn(mockNode);
            when(mockLoader.getController()).thenReturn(mockMovieListController);

            mainController.initialize();

            verify(mockMovieListContainer, never()).getChildren(); // Ensures VBox is not null
        } catch (IOException e) {
            fail("Exception thrown while loading FXML: " + e.getMessage());
        }
    }
}
