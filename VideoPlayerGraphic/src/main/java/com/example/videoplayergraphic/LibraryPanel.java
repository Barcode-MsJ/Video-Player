package com.example.videoplayergraphic;

import Controller.UserController;
import Model.Category;
import Model.Format;
import Model.Quality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LibraryPanel implements Initializable {

    @FXML
    private Label Channel;

    @FXML
    private TextField followChannelNameTextField;

    @FXML
    private Button registrationFCButten;

    @FXML
    private Label Subscriptions;

    @FXML
    private Button createNewChannelButten;

    @FXML
    private Button registrationCNCButten;

    @FXML
    private TextField newChannelCoverTextField;

    @FXML
    private TextField newChannelDescriptionTextField;

    @FXML
    private TextField newChannelNameTextField;

    @FXML
    private Label UserAccountName;

    @FXML
    private Button addContentPlaylistButten;

    @FXML
    private TextField category;

    @FXML
    private TextField contentIDTextField;

    @FXML
    private VBox contentInfoVbox;

    @FXML
    private TextField contentLink;

    @FXML
    private TextField contentTitle;

    @FXML
    private MenuButton contentType;

    @FXML
    private TextField cover;

    @FXML
    private Button ctreateButtern;

    @FXML
    private TextField description;

    @FXML
    private TextField duration;

    @FXML
    private Label home;

    @FXML
    private TextField isExclusive;

    @FXML
    private TextField liveSchedule;

    @FXML
    private Button makePlaylistButten;

    @FXML
    private TextField newPlaylistTextField;

    @FXML
    private VBox playliseVbox;

    @FXML
    private TextField playlistNameTextField;

    @FXML
    private TextField podcastOwner;

    @FXML
    private Button registrationNPNButten;

    @FXML
    private Button registrationPNCButten;

    @FXML
    private TextField shortAudio;

    @FXML
    private Circle userProfile;

    @FXML
    private Label userProfilelabel;

    @FXML
    private TextField videoFormat;

    @FXML
    private TextField videoQuality;

    @FXML
    private TextField videoSubtitles;

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
    void addContentPlaylistButtenClicked(ActionEvent event) {
        playlistNameTextField.setVisible(true);
        contentIDTextField.setVisible(true);
        registrationPNCButten.setVisible(true);
    }

    @FXML
    void makePlaylistButtenClicked(ActionEvent event) {
        newPlaylistTextField.setVisible(true);
        registrationNPNButten.setVisible(true);
    }

    @FXML
    void registrationNPNButtenClicked(ActionEvent event) {
        String newPlaylistName = this.newPlaylistTextField.getText();
        if(newPlaylistName != null){
            String returnText =UserController.getUserController().makePlaylist(newPlaylistName);
            showAlertInformation(returnText);
        }
    }

    @FXML
    void registrationPNCButtenClicked(ActionEvent event) {
        String playlistName = this.playlistNameTextField.getText();
        int contentID = Integer.valueOf(this.contentIDTextField.getText());
        if(playlistName != null){
            String returnText = UserController.getUserController().addContentPlaylist(playlistName , contentID);
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

    private String selected;

    @FXML
    void ctreateButternClicked(ActionEvent event) throws ParseException {
        switch (selected){
            case "createShortVideo" ->{
                String contentTitle = this.contentTitle.getText();
                boolean isExclusive = Boolean.parseBoolean(this.isExclusive.getText());
                String description = this.description.getText();
                long duration = Long.parseLong(this.duration.getText());
                Category category = Category.valueOf(this.category.getText());
                String contentLink = this.contentLink.getText();
                String cover = this.cover.getText();
                String videoSubtitles = this.videoSubtitles.getText();
                String shortAudio = this.shortAudio.getText();

                if(contentTitle == null || description == null || duration == 0 || category == null || contentLink == null || cover == null || videoSubtitles == null || shortAudio == null){
                    showAlertWarning("Please fill in all fields.");
                }else {
                    String returnText = UserController.getUserController().createShortVideoContent(contentTitle,isExclusive,description,duration,category,contentLink,cover,videoSubtitles,shortAudio);
                    showAlertInformation(returnText);

                    this.contentTitle.clear();
                    this.isExclusive.clear();
                    this.description.clear();
                    this.duration.clear();
                    this.category.clear();
                    this.contentLink.clear();
                    this.cover.clear();
                    this.videoSubtitles.clear();
                    this.shortAudio.clear();
                }
            }
            case "createNormalVideo" ->{
                String contentTitle = this.contentTitle.getText();
                boolean isExclusive = Boolean.parseBoolean(this.isExclusive.getText());
                String description = this.description.getText();
                long duration = Long.parseLong(this.duration.getText());
                Category category = Category.valueOf(this.category.getText());
                String contentLink = this.contentLink.getText();
                String cover = this.cover.getText();
                String videoSubtitles = this.videoSubtitles.getText();
                Quality quality = Quality.valueOf(this.videoQuality.getText());
                Format format = Format.valueOf(this.videoFormat.getText());

                if(contentTitle == null || description == null || duration == 0 || category == null || contentLink == null || cover == null || videoSubtitles == null || quality == null || format == null){
                    showAlertWarning("Please fill in all fields.");
                }else {
                    String returnText = UserController.getUserController().createNormalVideoContent(contentTitle,isExclusive,description,duration,category,contentLink,cover,videoSubtitles,quality,format);
                    showAlertInformation(returnText);

                    this.contentTitle.clear();
                    this.isExclusive.clear();
                    this.description.clear();
                    this.duration.clear();
                    this.category.clear();
                    this.contentLink.clear();
                    this.cover.clear();
                    this.videoSubtitles.clear();
                    this.videoQuality.clear();
                    this.videoFormat.clear();
                }
            }
            case "createLiveStream" ->{
                String contentTitle = this.contentTitle.getText();
                boolean isExclusive = Boolean.parseBoolean(this.isExclusive.getText());
                String description = this.description.getText();
                long duration = Long.parseLong(this.duration.getText());
                Category category = Category.valueOf(this.category.getText());
                String contentLink = this.contentLink.getText();
                String cover = this.cover.getText();
                String videoSubtitles = this.videoSubtitles.getText();
                String dateStr = this.liveSchedule.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date liveSchedule = formatter.parse(dateStr);

                if(contentTitle == null || description == null || duration == 0 || category == null || contentLink == null || cover == null || videoSubtitles == null || liveSchedule == null){
                    showAlertWarning("Please fill in all fields.");
                }else {
                    String returnText = UserController.getUserController().createLiveStreamContent(contentTitle,isExclusive,description,duration,category,contentLink,cover,videoSubtitles,liveSchedule);
                    showAlertInformation(returnText);

                    this.contentTitle.clear();
                    this.isExclusive.clear();
                    this.description.clear();
                    this.duration.clear();
                    this.category.clear();
                    this.contentLink.clear();
                    this.cover.clear();
                    this.videoSubtitles.clear();
                    this.liveSchedule.clear();
                }
            }
            case "createPodcast" ->{
                String contentTitle = this.contentTitle.getText();
                boolean isExclusive = Boolean.parseBoolean(this.isExclusive.getText());
                String description = this.description.getText();
                long duration = Long.parseLong(this.duration.getText());
                Category category = Category.valueOf(this.category.getText());
                String contentLink = this.contentLink.getText();
                String cover = this.cover.getText();
                String podcastOwner = this.podcastOwner.getText();

                if(contentTitle == null || description == null || duration == 0 || category == null || contentLink == null || cover == null || podcastOwner == null){
                    showAlertWarning("Please fill in all fields.");
                }else {
                    String returnText = UserController.getUserController().createPodcastContent(contentTitle,isExclusive,description,duration,category,contentLink,cover,podcastOwner);
                    showAlertInformation(returnText);

                    this.contentTitle.clear();
                    this.isExclusive.clear();
                    this.description.clear();
                    this.duration.clear();
                    this.category.clear();
                    this.contentLink.clear();
                    this.cover.clear();
                    this.podcastOwner.clear();
                }
            }
        }
    }

    @FXML
    void createNewChannelButtenClicked(ActionEvent event) {
        newChannelNameTextField.setVisible(true);
        newChannelDescriptionTextField.setVisible(true);
        newChannelCoverTextField.setVisible(true);
        registrationCNCButten.setVisible(true);
    }

    @FXML
    void registrationCNCButtenClicked(ActionEvent event) {
        String newChannelName = this.newChannelNameTextField.getText();
        String newChannelDescription = this.newChannelDescriptionTextField.getText();
        String newChannelCover = this.newChannelCoverTextField.getText();
        if(newChannelName != null || newChannelDescription != null || newChannelCover != null){
            String returnText = UserController.getUserController().createNewChannel(newChannelName,newChannelDescription,newChannelCover);
            showAlertInformation(returnText);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = new File(UserController.getUserController().getLoggedInUser().getProfileCover()).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        userProfile.setFill(pattern);

        UserAccountName.setText(UserController.getUserController().getLoggedInUser().getAccountName());

        String command = UserController.getUserController().showPlaylistInfo().toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0 ; i < commandInfo.length ; i++){
            String[] commandInfo2 = commandInfo[i].split(" ");

            Label playlistNameLabel = new Label(commandInfo2[0]);
            playlistNameLabel.setFont(Font.font("IRANSans", FontWeight.BOLD, 20));
            playlistNameLabel.setAlignment(Pos.CENTER);
            playlistNameLabel.setPadding(new Insets(10));
            playlistNameLabel.setStyle("-fx-text-fill: #E62117; -fx-background-radius: 5; -fx-border-color: #999; -fx-border-radius: 5;");

            playliseVbox.getChildren().add(playlistNameLabel);

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

            playliseVbox.getChildren().add(gridPane);
        }

        newPlaylistTextField.setVisible(false);
        registrationNPNButten.setVisible(false);
        registrationPNCButten.setVisible(false);
        playlistNameTextField.setVisible(false);
        contentIDTextField.setVisible(false);
        newChannelCoverTextField.setVisible(false);
        newChannelDescriptionTextField.setVisible(false);
        newChannelNameTextField.setVisible(false);
        registrationCNCButten.setVisible(false);
        followChannelNameTextField.setVisible(false);
        registrationFCButten.setVisible(false);

        MenuItem createShortVideo = new MenuItem("Create Short Video Content");
        MenuItem createNormalVideo = new MenuItem("Create Normal Video Content");
        MenuItem createLiveStream = new MenuItem("Create Live Stream Content");
        MenuItem createPodcast = new MenuItem("Create Podcast Content");
        contentType.getItems().addAll(createShortVideo , createNormalVideo , createLiveStream , createPodcast);

        contentInfoVbox.setVisible(false);
        ctreateButtern.setVisible(false);

        createShortVideo.setOnAction(e -> setInputs("createShortVideo"));
        createNormalVideo.setOnAction(e -> setInputs("createNormalVideo"));
        createLiveStream.setOnAction(e -> setInputs("createLiveStream"));
        createPodcast.setOnAction(e -> setInputs("createPodcast"));

    }

    private void setInputs(String selectedItem){
        selected = selectedItem;
        contentInfoVbox.setVisible(true);
        ctreateButtern.setVisible(true);
        videoSubtitles.setManaged(false);
        videoSubtitles.setVisible(false);
        shortAudio.setManaged(false);
        shortAudio.setVisible(false);
        videoQuality.setManaged(false);
        videoQuality.setVisible(false);
        videoFormat.setManaged(false);
        videoFormat.setVisible(false);
        liveSchedule.setManaged(false);
        liveSchedule.setVisible(false);
        podcastOwner.setManaged(false);
        podcastOwner.setVisible(false);
        switch (selectedItem){
            case "createShortVideo" ->{
                videoSubtitles.setManaged(true);
                videoSubtitles.setVisible(true);
                shortAudio.setManaged(true);
                shortAudio.setVisible(true);
            }
            case "createNormalVideo" ->{
                videoSubtitles.setManaged(true);
                videoSubtitles.setVisible(true);
                videoQuality.setManaged(true);
                videoQuality.setVisible(true);
                videoFormat.setManaged(true);
                videoFormat.setVisible(true);
            }
            case "createLiveStream" ->{
                videoSubtitles.setManaged(true);
                videoSubtitles.setVisible(true);
                liveSchedule.setManaged(true);
                liveSchedule.setVisible(true);
            }
            case "createPodcast" ->{
                podcastOwner.setManaged(true);
                podcastOwner.setVisible(true);
            }
        }
    }

    @FXML
    void registrationFCButtenClicked(ActionEvent event) {
        String followChannelName = this.followChannelNameTextField.getText();
        if(followChannelName != null){
            String returnText =UserController.getUserController().followChannel(followChannelName);
            showAlertInformation(returnText);
        }
    }

    @FXML
    void followChannelButtenClicked(ActionEvent event) {
        followChannelNameTextField.setVisible(true);
        registrationFCButten.setVisible(true);
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
