package com.intech.mysudoku.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SettingsScreenController implements Initializable {

    private Scene scene;
    TitleScreenController titleScreenController;
    Stage stage;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label titleLabel;
    @FXML
    Label soundsLabel;
    @FXML
    Label musicLabel;
    @FXML
    CheckBox musicCheckBox;
    @FXML
    Label volumeLabel;
    @FXML
    Slider volumeSlider;
    @FXML
    Label languageLabel;
    @FXML
    Label selectedLanguageLabel;
    @FXML
    ChoiceBox<Image> languageChoiceBox;
    @FXML
    Label guiLabel;
    @FXML
    Label colorAssistanceLabel;
    @FXML
    CheckBox colorAssistanceCheckBox;

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        languageChoiceBox.getItems().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/FR.png"))));
//        languageChoiceBox.getItems().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/EN.png"))));
    }

    public void toggleMusicCheckBox(ActionEvent event) {

    }

    public void toggleColorAssistanceCheckbox(ActionEvent event) {

    }
   public void handleShowMainMenu(ActionEvent event) throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
        Parent root = loader.load();
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();
    }
}
