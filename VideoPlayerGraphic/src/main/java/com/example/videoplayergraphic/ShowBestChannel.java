package com.example.videoplayergraphic;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ShowBestChannel {

    public void setVbox(Node node){
        bestChannelVbox.getChildren().add(node);
    }

    @FXML
    private VBox bestChannelVbox;

}
