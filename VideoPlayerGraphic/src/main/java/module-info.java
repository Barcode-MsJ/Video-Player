module com.example.videoplayergraphic {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.jfr;


    opens com.example.videoplayergraphic to javafx.fxml;
    exports com.example.videoplayergraphic;
    exports com.example.videoplayergraphic.StartPanel;
    opens com.example.videoplayergraphic.StartPanel to javafx.fxml;
}