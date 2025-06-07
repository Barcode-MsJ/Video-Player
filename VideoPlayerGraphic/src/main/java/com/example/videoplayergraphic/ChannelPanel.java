package com.example.videoplayergraphic;

import Controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChannelPanel implements Initializable {

    @FXML
    private Label Library;

    @FXML
    private Label Subscriptions;

    @FXML
    private Label UserAccountName;

    @FXML
    private Button addContentPlaylistButten;

    @FXML
    private Circle bigChannelProfile;

    @FXML
    private Button channelNameEdit;

    @FXML
    private Label channelNameLabel;

    @FXML
    private Label channelOwnerLabel;

    @FXML
    private Button channelProfileEdit;

    @FXML
    private VBox channelplayliseVbox;

    @FXML
    private TextField contentIDTextField;

    @FXML
    private Button descriptionEdit;

    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox followerVbox;

    @FXML
    private Label followersLabel;

    @FXML
    private Label home;

    @FXML
    private Button makeChannelPlaylistButten;

    @FXML
    private TextField newChannelPlaylistTextField;

    @FXML
    private TextField playlistNameTextField;

    @FXML
    private Button registrationACTCPButten;

    @FXML
    private Button registrationMNCPButten;

    @FXML
    private Circle userProfile;

    @FXML
    private Label userProfilelabel;

    @FXML
    void HomeClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Panel.fxml"));
        Scene userPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(userPanelScene);
        HelloApplication.getMainStage().setTitle("Home");
    }

    @FXML
    void LibraryClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Library_Panel.fxml"));
        Scene libraryPanel = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(libraryPanel);
        HelloApplication.getMainStage().setTitle("Library Panel");
    }

    @FXML
    void SubscriptionsClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Subscription.fxml"));
        Scene subscriptionScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(subscriptionScene);
        HelloApplication.getMainStage().setTitle("Subscription Panel");
    }

    @FXML
    void addContentPlaylistButtenClicked(ActionEvent event) {
        playlistNameTextField.setVisible(true);
        contentIDTextField.setVisible(true);
        registrationACTCPButten.setVisible(true);
    }

    @FXML
    void channelNameEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(channelNameLabel.getText());
        dialog.setTitle("Edit Channel Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new channel name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            channelNameLabel.setText(newName);
            UserController.getUserController().editChannelInfo(1 , newName);
        });
    }

    @FXML
    void channelProfileEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Channel Profile");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new channel profile:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            String path = new File(newName).toURI().toString();
            Image image = new Image(path);
            ImagePattern pattern = new ImagePattern(image);
            bigChannelProfile.setFill(pattern);
            UserController.getUserController().editChannelInfo(3 , newName);
        });
    }

    @FXML
    void descriptionEditClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(descriptionLabel.getText());
        dialog.setTitle("Edit Channel Description");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new channel description :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            descriptionLabel.setText(newName);
            UserController.getUserController().editChannelInfo(2 , newName);
        });
    }

    @FXML
    void makeChannelPlaylistButtenClicked(ActionEvent event) {
        newChannelPlaylistTextField.setVisible(true);
        registrationMNCPButten.setVisible(true);
    }

    @FXML
    void registrationACTCPButtenClicked(ActionEvent event) {
        String channelPlaylistName = this.playlistNameTextField.getText();
        int contentID = Integer.valueOf(this.contentIDTextField.getText());
        if(channelPlaylistName != null){
            String returnText = UserController.getUserController().addContentToChannelPlaylist(contentID,channelPlaylistName);
            showAlertInformation(returnText);
        }
    }

    @FXML
    void registrationMNCPButtenClicked(ActionEvent event) {
        String newChannelPlaylistName = this.newChannelPlaylistTextField.getText();
        if(newChannelPlaylistName != null){
            String returnText = UserController.getUserController().channelPlaylist(newChannelPlaylistName);
            showAlertInformation(returnText);
        }
    }

    @FXML
    void userProfileCliked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Profile.fxml"));
        Scene userProfileScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(userProfileScene);
        HelloApplication.getMainStage().setTitle("User Profile");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = new File(UserController.getUserController().getLoggedInUser().getProfileCover()).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        userProfile.setFill(pattern);

        UserAccountName.setText(UserController.getUserController().getLoggedInUser().getAccountName());

        channelNameLabel.setText(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelName());
        channelOwnerLabel.setText(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelOwner().getFirstNameAndLastName());
        descriptionLabel.setText(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelDescription());
        followersLabel.setText(String.valueOf(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelFollowers().size()));
        String path1 = new File(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelCover()).toURI().toString();
        Image image1 = new Image(path1);
        ImagePattern pattern1 = new ImagePattern(image1);
        bigChannelProfile.setFill(pattern1);

        String command = UserController.getUserController().showChannelInfo(UserController.getUserController().getLoggedInUser().getUserChannel().getChannelName()).toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0 ; i < commandInfo.length ; i++){
            String[] commandInfo2 = commandInfo[i].split(" ");

            Label playlistNameLabel = new Label(commandInfo2[0]);
            playlistNameLabel.setFont(Font.font("IRANSans", FontWeight.BOLD, 20));
            playlistNameLabel.setAlignment(Pos.CENTER);
            playlistNameLabel.setPadding(new Insets(10));
            playlistNameLabel.setStyle("-fx-text-fill: #E62117; -fx-background-radius: 5; -fx-border-color: #999; -fx-border-radius: 5;");

            channelplayliseVbox.getChildren().add(playlistNameLabel);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            int totalItems = commandInfo2.length;
            int columns = 3;

            for (int j = 1; j < totalItems; j++) {
                int row = (j-1) / columns;
                int col = (j-1) % columns;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Cover.fxml"));
                VBox videoNode;
                try {
                    videoNode = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                VideoCover videoCover = loader.getController();
                videoCover.setVideoLink(commandInfo2[j]);

                gridPane.add(videoNode, col, row);
            }

            channelplayliseVbox.getChildren().add(gridPane);
        }

        newChannelPlaylistTextField.setVisible(false);
        registrationMNCPButten.setVisible(false);
        playlistNameTextField.setVisible(false);
        contentIDTextField.setVisible(false);
        registrationACTCPButten.setVisible(false);

        String commandFollowers = UserController.getUserController().showFollowers().toString();
        String[] commandInfoFollowers = commandFollowers.split("\n");

        for(int i = 0 ; i < commandInfoFollowers.length ; i++) {
            String[] commandInfo2 = commandInfoFollowers[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_Cover.fxml"));
            AnchorPane userNode;
            try {
                userNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserCover userCover = loader.getController();
            userCover.setUserAccount(commandInfo2[0] , commandInfo2[1]);

            followerVbox.getChildren().add(userNode);
        }

    }

    private void showAlertInformation(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
