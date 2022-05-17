package com.intech.mysudoku.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class GameScreenController implements Initializable {

    private Scene Scene;
    private TitleScreenController titleScreenController;

    Stage stage;

    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane grid;
    @FXML
    Label titleLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Label chronoLabel;
    @FXML
    Button exitGameButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }




}
