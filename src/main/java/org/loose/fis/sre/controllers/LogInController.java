package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LogInController{

    @FXML
    private Button logInButton;
    @FXML
    private Button createNewAccountButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    private Stage stage;
    private Parent root;
    private Scene scene;

    //when log in button is pressed
    public void userLogIn(ActionEvent event) {
    }

    //when register button is pressed
    public void userCreateNewAccount(ActionEvent event) {
    }
}