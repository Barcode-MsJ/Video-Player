package com.example.videoplayergraphic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChannelCover {

    private String channelAccount;
    private String channelProfileLink;
    private int followers;

    public void setChannelCover(String channelAccount , String channelProfileLink , int followers){
        this.channelAccount = channelAccount;
        this.channelProfileLink = channelProfileLink;
        this.followers = followers;

        String path = new File(channelProfileLink).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        this.channelProfile.setFill(pattern);

        channelAccountName.setText(channelAccount);
        channelFollowers.setText(String.valueOf(followers));
    }

    @FXML
    private Label channelAccountName;

    @FXML
    private AnchorPane channelCoverAnchorPane;

    @FXML
    private Label channelFollowers;

    @FXML
    private Circle channelProfile;

    @FXML
    void channelCoverClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChannelInfo_Panel.fxml"));
        Parent root = loader.load();

         ChannelInfoPanel channelInfoPanel = loader.getController();
        channelInfoPanel.setChannelName(this.channelAccount);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Channel Information Panel");
        stage.showAndWait();
    }

}
