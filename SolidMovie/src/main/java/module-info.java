module com.solidmovie.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires static lombok;
    //requires org.mockito;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    opens com.solidmovie.app.Backend.Model to com.fasterxml.jackson.databind;
    opens com.solidmovie.app.Utils to com.fasterxml.jackson.databind;
    opens com.solidmovie.app to javafx.fxml;
    opens com.solidmovie.app.Frontend.Controllers to javafx.fxml;

    exports com.solidmovie.app;
}