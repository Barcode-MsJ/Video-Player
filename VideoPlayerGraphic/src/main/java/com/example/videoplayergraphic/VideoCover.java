package com.example.videoplayergraphic;

import Model.Content;
import Model.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class VideoCover {

    private String videoLink ;
    public void setVideoLink(String videoLink){
        this.videoLink = videoLink;
        String path = new File(videoLink).toURI().toString();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        video.setMediaPlayer(mediaPlayer);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(videoLink)){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile.setFill(imagePattern);
                contentTitel.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel.setText(text);
            }
        }
    }

    @FXML
    private Circle channelProfile;

    @FXML
    private Label contentTitel;

    @FXML
    private MediaView video;

    @FXML
    private Label viewLikeChannel;

    @FXML
    void video1Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(this.videoLink);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

}
