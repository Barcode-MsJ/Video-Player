package com.example.videoplayergraphic.StartPanel;

import com.example.videoplayergraphic.HelloApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StartPanel {

    @FXML
    private Button AdminLoginButten;

    @FXML
    private Button LoginButten;

    @FXML
    private Button QuitButten;

    @FXML
    private ImageView imageView;

    @FXML
    private Label label;

    @FXML
    private Button signupButten;

    @FXML
    void AdminLoginButtenclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin_Login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(loginScene);
        HelloApplication.getMainStage().setTitle("Admin_Login");
    }

    @FXML
    void LoginButtenclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(loginScene);
        HelloApplication.getMainStage().setTitle("Login");
    }

    @FXML
    void QuitButtenclick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void signupButtenclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Scene signupScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(signupScene);
        HelloApplication.getMainStage().setTitle("Signup");
    }

}
