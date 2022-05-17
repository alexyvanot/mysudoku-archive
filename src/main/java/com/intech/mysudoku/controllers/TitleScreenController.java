package com.intech.mysudoku.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleScreenController implements Initializable {

    private Scene startScene;
    Stage stage;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label titleLabel;
    @FXML
    Button newGameButton;
    @FXML
    Button settingsButton;
    @FXML
    Button difficultyButton;
    @FXML
    Button exitButton;

    public void setStartScene (Scene scene) {
        this.startScene = scene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void handleNewGame(ActionEvent event) throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController gameScreenController = loader.getController();
        gameScreenController.setTitleScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showSettings(ActionEvent event) throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/settingsScreen.fxml"));
        Parent root = loader.load();
        SettingsScreenController settingsScreenController = loader.getController();
        settingsScreenController.setTitleScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
