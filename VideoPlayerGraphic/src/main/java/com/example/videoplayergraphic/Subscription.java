package com.example.videoplayergraphic;

import Controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Subscription implements Initializable {

    @FXML
    private Label Channel;

    @FXML
    private Label Library;

    @FXML
    private Label UserAccountName;

    @FXML
    private VBox channelsVbox;

    @FXML
    private Label home;

    @FXML
    private Circle userProfile;

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
    void libraryClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Library_Panel.fxml"));
        Scene libraryPanel = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(libraryPanel);
        HelloApplication.getMainStage().setTitle("Library Panel");
    }

    @FXML
    void userProfileClicked(MouseEvent event) throws IOException {
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

        String command = UserController.getUserController().showSubscriptions().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0 ; i < commandInfo.length ; i++) {
            String[] commandInfo2 = commandInfo[i].split(" ");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Channel_Cover.fxml"));
            AnchorPane channelNode;
            try {
                channelNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChannelCover channelCover = loader.getController();
            channelCover.setChannelCover(commandInfo2[0] , commandInfo2[1] , Integer.parseInt(commandInfo2[2]));

            channelsVbox.getChildren().add(channelNode);
        }
    }
}
