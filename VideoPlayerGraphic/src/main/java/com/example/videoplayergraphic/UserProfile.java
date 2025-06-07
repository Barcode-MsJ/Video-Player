package com.example.videoplayergraphic;

import Controller.AccountController;
import Controller.UserController;
import Model.Premium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    @FXML
    private Label Channel;

    @FXML
    private Label Subscriptions;

    @FXML
    private Label amountLabel;

    @FXML
    private Label premiumeUser;

    @FXML
    private Label UserAccountName;

    @FXML
    private Button accountEdit;

    @FXML
    private Label accountLabel;

    @FXML
    private Circle bigUserProfile;

    @FXML
    private ComboBox<String> buyPremium;

    @FXML
    private Button emailEdit;

    @FXML
    private Label emailLabel;

    @FXML
    private Button favoriteEdit;

    @FXML
    private Label favoriteLabel;

    @FXML
    private Label home;

    @FXML
    private Label home3;

    @FXML
    private Label home4;

    @FXML
    private Button increaseCredit;

    @FXML
    private Button nameEdit;

    @FXML
    private Label nameLabel;

    @FXML
    private Button passwordEdit;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button phoneEdit;

    @FXML
    private Label phoneLabel;

    @FXML
    private Circle userProfile;

    @FXML
    private Button userProfileEdit;

    @FXML
    void ChannelClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Channel_Panel.fxml"));
        Scene channelPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(channelPanelScene);
        HelloApplication.getMainStage().setTitle("Channel Panel");
    }

    @FXML
    void HomeClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Panel.fxml"));
        Scene userPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(userPanelScene);
        HelloApplication.getMainStage().setTitle("Home");
    }

    @FXML
    void SubscriptionsClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Subscription.fxml"));
        Scene subscriptionScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(subscriptionScene);
        HelloApplication.getMainStage().setTitle("Subscription Panel");
    }

    @FXML
    void accountEditClike(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(accountLabel.getText());
        dialog.setTitle("Edit Account Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new account name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            accountLabel.setText(newName);
            UserAccountName.setText(newName);
            UserController.getUserController().editUserInfo(1 , newName);
        });
    }

    @FXML
    void buyPremiumClick(ActionEvent event) {
        String premiumType = buyPremium.getValue();
        String returnText = UserController.getUserController().buyPremium(Premium.valueOf(premiumType));
        showAlertInformation(returnText);
        amountLabel.setText(String.valueOf(UserController.getUserController().getLoggedInUser().getCredit()) + "$");
        if(UserController.getUserController().getLoggedInUser().getIsPremium())premiumeUser.setVisible(true);
    }

    @FXML
    void emailEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(emailLabel.getText());
        dialog.setTitle("Edit Email");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new email :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            if(!AccountController.isValidEmail(newName))showAlertWarning("Please enter your email in the correct format.");
            else {
                emailLabel.setText(newName);
                UserController.getUserController().editUserInfo(4, newName);
            }
        });
    }

    @FXML
    void favoriteEditClick(ActionEvent event) {
        new FavoriteCategory().start(new Stage());
        favoriteLabel.setText(UserController.getUserController().getLoggedInUser().getFavoriteCategories().toString());
    }

    @FXML
    void increaseCreditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Increase Credit");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter amount:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(credit -> {
            UserController.getUserController().increaseCredit(Long.valueOf(credit));
            amountLabel.setText(String.valueOf(UserController.getUserController().getLoggedInUser().getCredit()) + "$");
        });
    }

    @FXML
    void libraryClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Library_Panel.fxml"));
        Scene libraryPanel = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(libraryPanel);
        HelloApplication.getMainStage().setTitle("Library Panel");
    }

    @FXML
    void nameEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(nameLabel.getText());
        dialog.setTitle("Edit Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            nameLabel.setText(newName);
            UserController.getUserController().editUserInfo(3 , newName);
        });
    }

    @FXML
    void passwordEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(passwordLabel.getText());
        dialog.setTitle("Edit Password");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new password:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            passwordLabel.setText(newName);
            UserController.getUserController().editUserInfo(2 , newName);
        });
    }

    @FXML
    void phoneEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(phoneLabel.getText());
        dialog.setTitle("Edit Phone Number");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new phone number:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            if(!AccountController.isValidPhone(newName))showAlertWarning("Please enter your phone in the correct format.");
            else {
                phoneLabel.setText(newName);
                UserController.getUserController().editUserInfo(5 , newName);
            }
        });
    }

    @FXML
    void userProfileEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Profile");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new profile:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            String path = new File(newName).toURI().toString();
            Image image = new Image(path);
            ImagePattern pattern = new ImagePattern(image);
            userProfile.setFill(pattern);
            bigUserProfile.setFill(pattern);
            UserController.getUserController().editUserInfo(6 , newName);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = new File(UserController.getUserController().getLoggedInUser().getProfileCover()).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        userProfile.setFill(pattern);

        UserAccountName.setText(UserController.getUserController().getLoggedInUser().getAccountName());

        accountLabel.setText(UserController.getUserController().getLoggedInUser().getAccountName());
        nameLabel.setText(UserController.getUserController().getLoggedInUser().getFirstNameAndLastName());
        emailLabel.setText(UserController.getUserController().getLoggedInUser().getEmail());
        phoneLabel.setText(UserController.getUserController().getLoggedInUser().getPhoneNumber());
        passwordLabel.setText(UserController.getUserController().getLoggedInUser().getPassword());
        favoriteLabel.setText(UserController.getUserController().getLoggedInUser().getFavoriteCategories().toString());
        bigUserProfile.setFill(pattern);
        amountLabel.setText(String.valueOf(UserController.getUserController().getLoggedInUser().getCredit()) + "$");
        if(UserController.getUserController().getLoggedInUser().getIsPremium() == false)premiumeUser.setVisible(false);

        buyPremium.getItems().addAll("BRONZE" , "SILVER" , "GOLD");

    }

    @FXML
    void logoutButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Start_Panel.fxml"));
        Scene startPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(startPanelScene);
        HelloApplication.getMainStage().setTitle("Start Panel");
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
