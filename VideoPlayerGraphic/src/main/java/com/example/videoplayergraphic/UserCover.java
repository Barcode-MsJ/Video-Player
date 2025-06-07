package com.example.videoplayergraphic;

import Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class UserCover {

    private String userAccountName ;
    private String userProfileLink ;

    @FXML
    private Label UserAccountName;

    @FXML
    private AnchorPane userCoverAnchorPane;

    @FXML
    private Circle userProfile;

    public void setUserAccount(String userAccount , String userProfile){
        this.userAccountName = userAccount;
        this.userProfileLink = userProfile;

        String path = new File(userProfile).toURI().toString();
        Image image = new Image(path);
        ImagePattern pattern = new ImagePattern(image);
        this.userProfile.setFill(pattern);

        UserAccountName.setText(userAccount);
    }

}
