package com.example.videoplayergraphic.StartPanel;

import Controller.AdminController;
import com.example.videoplayergraphic.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLogin {

    @FXML
    private TextField accountName;

    @FXML
    private Button backButten;

    @FXML
    private Button loginButten;

    @FXML
    private TextField password;

    @FXML
    void backButtenclick(ActionEvent event) {
        HelloApplication.getMainStage().setScene(HelloApplication.getMainScene());
    }

    @FXML
    void loginButtenClick(ActionEvent event) throws IOException {
        String accountName2 = accountName.getText();
        String password2 = password.getText();

        if (accountName2.isEmpty() || password2.isEmpty()){
            showAlertWarning("Please fill in all fields.");
        }else {
            String returnText = AdminController.getAdminController().adminLogin(accountName2 , password2);
            showAlertInformation(returnText);

            accountName.clear();
            password.clear();

            if(returnText.equals("Your login was successful.")) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin_Panel.fxml"));
                Scene adminPanelScene = new Scene(fxmlLoader.load());
                HelloApplication.getMainStage().setScene(adminPanelScene);
                HelloApplication.getMainStage().setTitle("Admin Panel");
            }
        }
    }
    private void showAlertWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void showAlertInformation(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
