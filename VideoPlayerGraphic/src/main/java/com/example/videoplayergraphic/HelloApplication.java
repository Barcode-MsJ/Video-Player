package com.example.videoplayergraphic;

import Controller.UserController;
import Model.Admin;
import Model.Category;
import Model.Format;
import Model.Quality;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage mainStage;
    private static Scene mainScene;

    public static Stage getMainStage() { return mainStage; }
    public static Scene getMainScene() { return mainScene; }

    @Override
    public void start(Stage stage) throws IOException {
        this.mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Start_Panel.fxml"));
        mainScene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Welcome YouTube!");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        Admin.getAdmin("MsJ" ,"666666" ,"MohammadSadeghJeihani",
                "jeihanim@gmail.com","09051419917","src/main/resources/image/youtube.png");
        UserController.getUserController().signUp("msj" , "666666" , "Mohammad" ,
                "jeihanim1@gmail.com" , "09051419917" , "src/main/resources/image/profile1.jpg" ,
                Category.Game , Category.History , Category.News , Category.Live);
        UserController.getUserController().signUp("msj2" , "666666" , "Mohammad" ,
                "jeihanim2@gmail.com" , "09051419917" , "src/main/resources/image/profile1.jpg" ,
                Category.Game , Category.History , Category.News , Category.Live);
        UserController.getUserController().signIn("msj2" , "666666");
        UserController.getUserController().createNormalVideoContent("5 second" , false , "salam" , 9 , Category.Game , "src/main/resources/video/video1.mp4" , "src/main/resources/image/channel1.png" , "" , Quality.LOW , Format.MKV);
        UserController.getUserController().createNormalVideoContent("Still alive" , false , "goodnight" , 4 , Category.News , "src/main/resources/video/video2.mp4" , "src/main/resources/image/channel1.png" , "" , Quality.LOW , Format.MKV);
        UserController.getUserController().createNewChannel("Dr.66" , "Im Dr.66" , "src/main/resources/image/channel1.png");
        UserController.getUserController().addContentToChannel(1);
        UserController.getUserController().addContentToChannel(2);
        UserController.getUserController().reportContent("src/main/resources/video/video1.mp4" , "bad");
        UserController.getUserController().followChannel("Dr.66");

        launch();
    }
}