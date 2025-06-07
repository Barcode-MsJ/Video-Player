package com.example.videoplayergraphic.StartPanel;

import Controller.AccountController;
import Controller.UserController;
import Model.Category;
import com.example.videoplayergraphic.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Signup implements Initializable {

    @FXML
    private ComboBox<String> Favoritecategory1;

    @FXML
    private ComboBox<String> Favoritecategory2;

    @FXML
    private ComboBox<String> Favoritecategory3;

    @FXML
    private ComboBox<String> Favoritecategory4;

    @FXML
    private TextField accountName;

    @FXML
    private Button backButten;

    @FXML
    private TextField email;

    @FXML
    private TextField firstNameAndLastName;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField profileCover;

    @FXML
    void backButtenclick(ActionEvent event) {
        HelloApplication.getMainStage().setScene(HelloApplication.getMainScene());
    }
    @FXML
    void registrationButtenClick(ActionEvent event) {
        String accountName2 = accountName.getText();
        String password2 = password.getText();
        String firstNameAndLastName2 = firstNameAndLastName.getText();
        String email2 = email.getText();
        String phoneNumber2 = phoneNumber.getText();
        String profileCover2 = profileCover.getText();
        String favoritecategory1 = Favoritecategory1.getValue();
        String favoritecategory2 = Favoritecategory2.getValue();
        String favoritecategory3 = Favoritecategory3.getValue();
        String favoritecategory4 = Favoritecategory4.getValue();

        if (accountName2.isEmpty() || password2.isEmpty() || firstNameAndLastName2.isEmpty() || email2.isEmpty() ||
                phoneNumber2.isEmpty() || profileCover2.isEmpty() || favoritecategory1 == null || favoritecategory2 == null ||
                favoritecategory3 == null || favoritecategory4 == null || favoritecategory1.isEmpty() || favoritecategory2.isEmpty() ||
                favoritecategory3.isEmpty() || favoritecategory4.isEmpty()){
            showAlertWarning("Please fill in all fields.");
        } else if(!AccountController.isValidPhone(phoneNumber2) || !AccountController.isValidEmail(email2)){
            showAlertWarning("Please enter your mobile phone and email in the correct format.");
        }else {
            String returnText = UserController.getUserController().signUp(accountName2, password2, firstNameAndLastName2, email2, phoneNumber2, profileCover2, Category.valueOf(favoritecategory1), Category.valueOf(favoritecategory2), Category.valueOf(favoritecategory3), Category.valueOf(favoritecategory4));
            showAlertInformation(returnText);

            accountName.clear();
            password.clear();
            firstNameAndLastName.clear();
            email.clear();
            phoneNumber.clear();
            profileCover.clear();
            Favoritecategory1.setValue(null);
            Favoritecategory2.setValue(null);
            Favoritecategory3.setValue(null);
            Favoritecategory4.setValue(null);
            HelloApplication.getMainStage().setScene(HelloApplication.getMainScene());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Favoritecategory1.getItems().addAll( "News" , "Game" , "Podcast" , "Music" , "Live" , "Society" , "History");
        Favoritecategory2.getItems().addAll( "News" , "Game" , "Podcast" , "Music" , "Live" , "Society" , "History");
        Favoritecategory3.getItems().addAll( "News" , "Game" , "Podcast" , "Music" , "Live" , "Society" , "History");
        Favoritecategory4.getItems().addAll( "News" , "Game" , "Podcast" , "Music" , "Live" , "Society" , "History");
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
