package com.intech.mysudoku.controllers;

import com.intech.mysudoku.tools.Creator;
import com.intech.mysudoku.tools.Iteration;
import com.intech.mysudoku.tools.Level;
import javafx.application.Platform;
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
    Scene startScene;
    Stage stage;

    Level difficulty = Level.BEGINNER;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStartScene (Scene scene) {
        this.startScene = scene;
    }

    public void handleNewGame(ActionEvent event) throws IOException {
        System.out.println("new game clicked ...");
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController gameScreenController = loader.getController();
        gameScreenController.setTitleScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleShowSettings(ActionEvent event) throws IOException {
        System.out.println("settings have been clicked ...");
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/settingsScreen.fxml"));
        Parent root = loader.load();
        SettingsScreenController settingsScreenController = loader.getController();
        settingsScreenController.setTitleScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleExit(ActionEvent event) {
        System.out.println("Quit button has been clicked ...");
        Platform.exit();
    }

}