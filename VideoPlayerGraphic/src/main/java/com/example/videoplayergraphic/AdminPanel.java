package com.example.videoplayergraphic;

import Controller.AdminController;
import Model.Content;
import Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanel implements Initializable {

    @FXML
    private Label accountLabel;

    @FXML
    private Circle bigUserProfile;

    @FXML
    private Button blockUserButten;

    @FXML
    private TextField blockUserIDTextField;

    @FXML
    private Button deleteContentButten;

    @FXML
    private TextField deleteContentIDTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button logoutButten;

    @FXML
    private Label nameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private ListView<String> reportsListView;

    @FXML
    private Button showBestChannelsButten;

    @FXML
    private Button showBestContentsButten;

    @FXML
    private Button showContentsInfoButten;

    @FXML
    private Button showUsersInfoButten;

    @FXML
    private Button unblockUserButten;

    @FXML
    private TextField unblockUserIDTextField;

    @FXML
    void blockUserButtenClicked(ActionEvent event) {
        long blockUserID = Long.parseLong(blockUserIDTextField.getText());
        blockUserIDTextField.clear();
        String returnText = AdminController.getAdminController().blockUser(blockUserID);
        showAlertInformation(returnText);
    }

    @FXML
    void deleteContentButtenClicked(ActionEvent event) {
        long deleteContentID = Long.parseLong(deleteContentIDTextField.getText());
        deleteContentIDTextField.clear();
        String returnText = AdminController.getAdminController().deleteContent(deleteContentID);
        showAlertInformation(returnText);
    }

    @FXML
    void logoutButtenClicked(ActionEvent event) throws IOException {
        AdminController.getAdminController().adminLogout();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Start_Panel.fxml"));
        Scene startPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(startPanelScene);
        HelloApplication.getMainStage().setTitle("Start Panel");
    }

    @FXML
    void showBestChannelsButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String command = AdminController.getAdminController().showBestChannels().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0; i < commandInfo.length; i++) {
            String[] commandInfo2 = commandInfo[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Channel_Cover.fxml"));
            AnchorPane channelNode;
            try {
                channelNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChannelCover channelCover = loader.getController();
            channelCover.setChannelCover(commandInfo2[0], commandInfo2[1], Integer.parseInt(commandInfo2[2]));

            controller.setVbox(channelNode);

        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Show Best Channels");
        stage.show();
    }


    @FXML
    void showBestContentsButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String command = AdminController.getAdminController().showBestContent().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0; i < commandInfo.length; i++) {
            String[] commandInfo2 = commandInfo[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Cover.fxml"));
            VBox videoNode;
            try {
                videoNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VideoCover videoCover = loader.getController();
            videoCover.setVideoLink(commandInfo2[0]);

            controller.setVbox(videoNode);

        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Show Best Contents");
        stage.show();
    }

    @FXML
    void showContentsInfoButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String command = AdminController.getAdminController().showContentInfo().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0; i < commandInfo.length; i++) {
            String[] commandInfo2 = commandInfo[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Cover.fxml"));
            VBox videoNode;
            try {
                videoNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VideoCover videoCover = loader.getController();
            videoCover.setVideoLink(commandInfo2[0]);

            controller.setVbox(videoNode);

        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Show Best Contents");
        stage.show();
    }

    @FXML
    void showUsersInfoButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String command = AdminController.getAdminController().showUserInfo().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0; i < commandInfo.length; i++) {
            String[] commandInfo2 = commandInfo[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_Cover.fxml"));
            AnchorPane userNode;
            try {
                userNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserCover userCover = loader.getController();
            userCover.setUserAccount(commandInfo2[0],commandInfo2[1]);

            controller.setVbox(userNode);

        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Show Best Contents");
        stage.show();
    }

    @FXML
    void unblockUserButtenClicked(ActionEvent event) {
        long unblockUserID = Long.parseLong(unblockUserIDTextField.getText());
        unblockUserIDTextField.clear();
        String returnText = AdminController.getAdminController().unblockUser(unblockUserID);
        showAlertInformation(returnText);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = new File(Database.getDatabase().getAdmin().getProfileCover()).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        bigUserProfile.setFill(pattern);

        accountLabel.setText(Database.getDatabase().getAdmin().getAccountName());
        nameLabel.setText(Database.getDatabase().getAdmin().getFirstNameAndLastName());
        emailLabel.setText(Database.getDatabase().getAdmin().getEmail());
        phoneLabel.setText(Database.getDatabase().getAdmin().getPhoneNumber());
        passwordLabel.setText(Database.getDatabase().getAdmin().getPassword());

        String command = AdminController.getAdminController().showReports().toString();
        String[] commandInfo = command.split("\n");
        for(int i = 0 ; i < commandInfo.length ; i++){
            reportsListView.getItems().add(commandInfo[i]);
        }
    }
    private void showAlertInformation(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
