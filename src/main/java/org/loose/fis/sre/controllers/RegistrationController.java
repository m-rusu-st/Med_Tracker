package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;
import javafx.scene.Parent;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.EventObject;

public class RegistrationController{

    @FXML
    private TextField LastNameField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private ChoiceBox<String> ChoiceBox;
    @FXML
    private Button Register;
    @FXML
    private Button Back;
    @FXML
    private Label emptyField;

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    public void initialize() {
        ChoiceBox.getItems().addAll("Pacient", "Medic");
    }

    public void goBack(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    /*@FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(UsernameBox.getText(), ParolaBox.getText(), (String) choiceBox.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
   }*/

    public void createAccount(ActionEvent event) throws IOException, UsernameAlreadyExistsException, NoEmptyField
    {

        try{
            int check = UserService.addUser(LastNameField.getText(), FirstNameField.getText(), PhoneField.getText(), AddressField.getText(), UsernameField.getText(), PasswordField.getText(), (String) ChoiceBox.getValue());

            if(check == 1){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else if(check == 2){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch(NoEmptyField e){
            emptyField.setText(e.getMessage());
        }catch(UsernameAlreadyExistsException e){
            emptyField.setText(e.getMessage());
        }
    }
}
