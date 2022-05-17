package com.intech.mysudoku.controllers;

import java.awt.Checkbox;
import java.awt.Label;

import com.intech.mysudoku.config.Language;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class SettingsScreenController {

    private Scene scene;
    private TitleScreenController titleScreenController;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label titleLabel;
    @FXML
    Label soundsLabel;
    @FXML
    Label musicLabel;
    @FXML
    Checkbox musicCheckBox;
    @FXML
    Label volumeLabel;
    @FXML
    Slider volumeSlider;
    @FXML
    Label languageLabel;
    @FXML
    Label selectedLanguageLabel;
    @FXML
    ChoiceBox<Language> languageChoiceBox;
    @FXML
    Label guiLabel;
    @FXML
    Label colorAssistanceLabel;
    @FXML
    Checkbox colorAssistanceCheckBox;

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

}
