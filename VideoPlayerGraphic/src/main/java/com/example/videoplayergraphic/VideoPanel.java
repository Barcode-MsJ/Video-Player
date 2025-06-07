package com.example.videoplayergraphic;

import Controller.UserController;
import Model.Content;
import Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class VideoPanel {

    MediaPlayer mediaPlayer;

    private String videoLink ;
    public void setVideoLink(String videoLink){
        this.videoLink = videoLink;
        videoLink = new File(this.videoLink).toURI().toString();
        Media media = new Media(videoLink);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        this.mediaPlayer = mediaPlayer;
        mediaPlayer.play();

        soundSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newVal.doubleValue());
            }
        });

        mediaPlayer.setOnReady(() -> {
            videoSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
            videoTime.setText("00:00 / " + formatTime(mediaPlayer.getTotalDuration()));
        });
        mediaPlayer.currentTimeProperty().addListener((obs, old, now) -> {
            videoSlider.setValue(now.toSeconds());
            videoTime.setText(formatTime(now) + " / " + formatTime(mediaPlayer.getTotalDuration()));
        });
        videoSlider.setOnMouseReleased(e -> mediaPlayer.seek(Duration.seconds(videoSlider.getValue())));
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);

        mediaPlayer.setVolume(soundSlider.getValue());

        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(this.videoLink)){
                likes.setText("Likes :" + String.valueOf(content.getLikes()));
                UserController.getUserController().playContent(this.videoLink);
                views.setText("Views : " + String.valueOf(content.getViews()));
                description.setText(content.getDescription());
                break;
            }
        }
    }

    @FXML
    private TextField comment;

    @FXML
    private Label description;

    @FXML
    private Button likeButten;

    @FXML
    private Label likes;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button pauseButten;

    @FXML
    private Button playButten;

    @FXML
    private Button reportButten;

    @FXML
    private Button sendButten;

    @FXML
    private Slider soundSlider;

    @FXML
    private Button stopButten;

    @FXML
    private Slider videoSlider;

    @FXML
    private Label videoTime;

    @FXML
    private Label views;

    private boolean isLiked = false;
    @FXML
    void likeButtenClicked(ActionEvent event) {
        isLiked = !isLiked;
        if (isLiked) {
            likeButten.setText("Liked");
            likeButten.setStyle("-fx-background-color: lightblue;");
            UserController.getUserController().like(videoLink);
            for(Content content : Database.getDatabase().getAllContents()){
                if(content.getContentLink().equals(videoLink)){
                    likes.setText("Likes :" + String.valueOf(content.getLikes()));
                    break;
                }
            }
        } else {
            likeButten.setText("Like\uD83D\uDC4D");
            likeButten.setStyle("");
            for(int i = 0; i < Database.getDatabase().getAllContents().size() ; i++) {
                if (Database.getDatabase().getAllContents().get(i).getContentLink().equals(videoLink)) {
                    Database.getDatabase().getAllContents().get(i).setLikes(Database.getDatabase().getAllContents().get(i).getLikes()-1);
                    likes.setText("Likes :" + String.valueOf(Database.getDatabase().getAllContents().get(i).getLikes()));
                    break;
                }
            }
        }
    }

    @FXML
    void pauseButtenClicked(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void playButtenClicked(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void reportButtenClicked(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Report text . . . ");
        dialog.setTitle("Report");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Report text :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(report -> {
            UserController.getUserController().reportContent(videoLink , report);
        });
        reportButten.setText("Reported");
        reportButten.setStyle("-fx-background-color: lightblue;");
    }

    @FXML
    void sendButtenClicked(ActionEvent event) {
        String newComment = comment.getText();
        if(newComment != null){
            UserController.getUserController().comment(videoLink , newComment);
        }
        comment.clear();
    }

    @FXML
    void stopButtenClicked(ActionEvent event) {
        mediaPlayer.stop();
    }

    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60);
        return String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds);
    }
}
