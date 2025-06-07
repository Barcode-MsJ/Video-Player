package com.example.videoplayergraphic;

import Controller.UserController;
import Model.Category;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCategory extends Application {

    private final List<CheckBox> selectedBoxes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edit Favorite Categories");

        Label titleLabel = new Label("\uD83D\uDD8B Edit Favorite Categories");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setTextFill(Color.web("#E62117"));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        String[] categories = {
                "News", "Game", "Podcast",
                "Music", "Live", "Society",
                "History", "News", "Game"
        };

        CheckBox[] checkBoxes = new CheckBox[9];

        for (int i = 0; i < 9; i++) {
            CheckBox cb = new CheckBox(categories[i]);
            cb.setPrefWidth(100);
            cb.setPrefHeight(40);
            int row = i / 3;
            int col = i % 3;
            gridPane.add(cb, col, row);

            cb.setOnAction(e -> {
                if (cb.isSelected()) {
                    if (selectedBoxes.size() < 4) {
                        cb.setStyle("-fx-background-color: #E62117; -fx-text-fill: white;");
                        selectedBoxes.add(cb);
                    } else {
                        cb.setSelected(false);
                    }
                } else {
                    cb.setStyle("");
                    selectedBoxes.remove(cb);
                }
            });

            checkBoxes[i] = cb;
        }

        Button confirmButton = new Button("Edit");
        confirmButton.setFont(new Font(16));
        confirmButton.setDisable(true);

        for (CheckBox cb : checkBoxes) {
            cb.setOnMouseClicked(e -> {
                if (selectedBoxes.size() == 4) {
                    confirmButton.setDisable(false);
                } else {
                    confirmButton.setDisable(true);
                }
            });
        }

        confirmButton.setOnAction(e -> {
            List<String> chosen = new ArrayList<>();
            for (CheckBox cb : selectedBoxes) {
                chosen.add(cb.getText());
            }
            UserController.getUserController().editUserCategory(Category.valueOf(chosen.get(0)) , Category.valueOf(chosen.get(1)) , Category.valueOf(chosen.get(2)) , Category.valueOf(chosen.get(3)));
            primaryStage.close();
        });

        VBox vbox = new VBox(20, titleLabel, gridPane, confirmButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
