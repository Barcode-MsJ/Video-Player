package com.example.videoplayergraphic;

import Controller.AdminController;
import Controller.UserController;
import Model.Content;
import Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPanel implements Initializable {

    private String videoLink01;
    private String videoLink02;
    private String videoLink03;
    private String videoLink04;
    private String videoLink05;
    private String videoLink06;
    private String videoLink07;
    private String videoLink08;
    private String videoLink09;

    @FXML
    private Label Channel;

    @FXML
    private Label Subscriptions;

    @FXML
    private Label UserAccountName;

    @FXML
    private Circle channelProfile;

    @FXML
    private Circle channelProfile1;

    @FXML
    private Circle channelProfile2;

    @FXML
    private Circle channelProfile3;

    @FXML
    private Circle channelProfile4;

    @FXML
    private Circle channelProfile5;

    @FXML
    private Circle channelProfile6;

    @FXML
    private Circle channelProfile7;

    @FXML
    private Circle channelProfile8;

    @FXML
    private Label contentTitel;

    @FXML
    private Label contentTitel1;

    @FXML
    private Label contentTitel2;

    @FXML
    private Label contentTitel3;

    @FXML
    private Label contentTitel4;

    @FXML
    private Label contentTitel5;

    @FXML
    private Label contentTitel6;

    @FXML
    private Label contentTitel7;

    @FXML
    private Label contentTitel8;

    @FXML
    private ComboBox<String> filter;

    @FXML
    private Label home;

    @FXML
    private Label home3;

    @FXML
    private Label home4;

    @FXML
    private Button searchButten;

    @FXML
    private TextField searchContent;

    @FXML
    private ComboBox<String> searchchannel;

    @FXML
    private ComboBox<String> sort;

    @FXML
    private Circle userProfile;

    @FXML
    private MediaView video;

    @FXML
    private MediaView video1;

    @FXML
    private MediaView video2;

    @FXML
    private MediaView video3;

    @FXML
    private MediaView video4;

    @FXML
    private MediaView video5;

    @FXML
    private MediaView video6;

    @FXML
    private MediaView video7;

    @FXML
    private MediaView video8;

    @FXML
    private Label viewLikeChannel;

    @FXML
    private Label viewLikeChannel1;

    @FXML
    private Label viewLikeChannel2;

    @FXML
    private Label viewLikeChannel3;

    @FXML
    private Label viewLikeChannel4;

    @FXML
    private Label viewLikeChannel5;

    @FXML
    private Label viewLikeChannel6;

    @FXML
    private Label viewLikeChannel7;

    @FXML
    private Label viewLikeChannel8;

    @FXML
    void ChannelClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Channel_Panel.fxml"));
        Scene channelPanelScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(channelPanelScene);
        HelloApplication.getMainStage().setTitle("Channel Panel");
    }

    @FXML
    void SubscriptionsClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Subscription.fxml"));
        Scene subscriptionScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(subscriptionScene);
        HelloApplication.getMainStage().setTitle("Subscription Panel");
    }

    @FXML
    void UserProfileClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Profile.fxml"));
        Scene userProfileScene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(userProfileScene);
        HelloApplication.getMainStage().setTitle("User Profile");
    }

    @FXML
    void filterClicked(ActionEvent event) {

    }

    @FXML
    void libraryClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Library_Panel.fxml"));
        Scene libraryPanel = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setScene(libraryPanel);
        HelloApplication.getMainStage().setTitle("Library Panel");
    }

    @FXML
    void searchButtenClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String searchContentName = searchContent.getText();
        searchContent.clear();

        String command = UserController.getUserController().searchContent(searchContentName).toString();
        String[] commandInfo = command.split("\n");

        for(int i = 0; i < commandInfo.length; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Cover.fxml"));
            VBox videoNode;
            try {
                videoNode = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VideoCover videoCover = loader.getController();
            videoCover.setVideoLink(commandInfo[i]);

            controller.setVbox(videoNode);

        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Search Content");
        stage.show();
    }

    @FXML
    void searchchannelClicked(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Show_Best_Channel.fxml"));
        Parent root = loader2.load();
        ShowBestChannel controller = loader2.getController();

        String inputText = searchchannel.getEditor().getText();

        String command = UserController.getUserController().searchChannel(inputText).toString();
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
        stage.setTitle("Search Channels");
        stage.show();
    }

    @FXML
    void sortClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort.getItems().addAll("View" , "Like" , "Date");
        filter.getItems().addAll("Video" , "Podcast" , "Category" , "Date");
        searchchannel.getItems().addAll("ALL Channel");

        String path = new File(UserController.getUserController().getLoggedInUser().getProfileCover()).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        userProfile.setFill(pattern);

        UserAccountName.setText(UserController.getUserController().getLoggedInUser().getAccountName());

        String command = UserController.getUserController().showSuggestions().toString();
        String[] commandInfo = command.split("\n");

        String path0 = new File(commandInfo[0]).toURI().toString();
        Media media0 = new Media(path0);
        MediaPlayer mediaPlayer0 = new MediaPlayer(media0);
        videoLink01 = commandInfo[0];
        video.setMediaPlayer(mediaPlayer0);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[0])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile.setFill(imagePattern);
                contentTitel.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel.setText(text);
            }
        }
        String path1 = new File(commandInfo[1]).toURI().toString();
        Media media1 = new Media(path1);
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        videoLink02 = commandInfo[1];
        video1.setMediaPlayer(mediaPlayer1);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[1])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile1.setFill(imagePattern);
                contentTitel1.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel1.setText(text);
            }
        }
        String path2 = new File(commandInfo[2]).toURI().toString();
        Media media2 = new Media(path2);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        videoLink03 = commandInfo[2];
        video2.setMediaPlayer(mediaPlayer2);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[2])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile2.setFill(imagePattern);
                contentTitel2.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel2.setText(text);
            }
        }
        String path3 = new File(commandInfo[3]).toURI().toString();
        Media media3 = new Media(path3);
        MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
        videoLink04 = commandInfo[3];
        video3.setMediaPlayer(mediaPlayer3);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[3])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile3.setFill(imagePattern);
                contentTitel3.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel3.setText(text);
            }
        }
        String path4 = new File(commandInfo[4]).toURI().toString();
        Media media4 = new Media(path4);
        MediaPlayer mediaPlayer4 = new MediaPlayer(media4);
        videoLink05 = commandInfo[4];
        video4.setMediaPlayer(mediaPlayer4);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[4])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile4.setFill(imagePattern);
                contentTitel4.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel4.setText(text);
            }
        }
        String path5 = new File(commandInfo[5]).toURI().toString();
        Media media5 = new Media(path5);
        MediaPlayer mediaPlayer5 = new MediaPlayer(media5);
        videoLink06 = commandInfo[5];
        video5.setMediaPlayer(mediaPlayer5);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[5])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile5.setFill(imagePattern);
                contentTitel5.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel5.setText(text);
            }
        }
        String path6 = new File(commandInfo[6]).toURI().toString();
        Media media6 = new Media(path6);
        MediaPlayer mediaPlayer6 = new MediaPlayer(media6);
        videoLink07 = commandInfo[6];
        video6.setMediaPlayer(mediaPlayer6);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[6])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile6.setFill(imagePattern);
                contentTitel6.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel6.setText(text);
            }
        }
        String path7 = new File(commandInfo[7]).toURI().toString();
        Media media7 = new Media(path7);
        MediaPlayer mediaPlayer7 = new MediaPlayer(media7);
        videoLink08 = commandInfo[7];
        video7.setMediaPlayer(mediaPlayer7);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[7])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile7.setFill(imagePattern);
                contentTitel7.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel7.setText(text);
            }
        }
        String path8 = new File(commandInfo[8]).toURI().toString();
        Media media8 = new Media(path8);
        MediaPlayer mediaPlayer8 = new MediaPlayer(media8);
        videoLink09 = commandInfo[8];
        video8.setMediaPlayer(mediaPlayer8);
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(commandInfo[8])){
                String channelPath = new File(content.getCover()).toURI().toString();
                Image image1 = new Image(channelPath);
                ImagePattern imagePattern = new ImagePattern(image1);
                channelProfile8.setFill(imagePattern);
                contentTitel8.setText(content.getContentTitle());
                String text = "view : " + content.getViews() + "/Likes : " + content.getLikes() + "/" + "Dr.66";
                viewLikeChannel8.setText(text);
            }
        }
    }

    @FXML
    void video1Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink01);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video2Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink02);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video3Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink03);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video4Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink04);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video5Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink05);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video6Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink06);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video7Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink07);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video8Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink08);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

    @FXML
    void video9Clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video_Panel.fxml"));
        Parent root = loader.load();

        VideoPanel controller = loader.getController();
        controller.setVideoLink(videoLink09);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Video Panel");
        stage.showAndWait();
    }

}
