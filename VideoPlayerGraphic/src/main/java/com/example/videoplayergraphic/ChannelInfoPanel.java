package com.example.videoplayergraphic;

import Controller.UserController;
import Model.Channel;
import Model.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;

public class ChannelInfoPanel {
    private String channelName;
    public void setChannelName(String channelName){
        this.channelName = channelName;

        for(Channel channel : Database.getDatabase().getAllChannels()){
            if(channel.getChannelName().equals(channelName)){
                channelNameLabel.setText(channelName);
                descriptionLabel.setText(channel.getChannelDescription());
                String path = new File(channel.getChannelCover()).toURI().toString();
                Image image = new Image(path);
                ImagePattern pattern = new ImagePattern(image);
                bigChannelProfile.setFill(pattern);
                followersLabel.setText(String.valueOf(channel.getChannelFollowers().size()));

                String command = UserController.getUserController().showChannelInfo(channelName).toString();
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
            }
        }
    }

    @FXML
    private Circle bigChannelProfile;

    @FXML
    private Label channelNameLabel;

    @FXML
    private VBox channelplayliseVbox;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label followersLabel;

}
