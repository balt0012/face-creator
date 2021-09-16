module com.example.facecreator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.facecreator to javafx.fxml;
    exports com.example.facecreator;
}