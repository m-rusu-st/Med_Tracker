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
    public void userLogIn(ActionEvent event) throws WrongUsernameException, WrongPasswordException, EmptyUsernameFieldException, EmptyPasswordFieldException, EmptyUsernamePasswordFieldException, IOException{
        try{
            int check = UserService.checkCredentials(usernameTextField.getText(), enterPasswordField.getText());

            if(check == 1)
            {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MakeAnAppointment.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            else if(check == 2){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
        }catch(EmptyUsernamePasswordFieldException e){
            wrongLogIn.setText(e.getMessage());
        }catch(EmptyUsernameFieldException e){
            wrongLogIn.setText(e.getMessage());
        }catch(EmptyPasswordFieldException e){
            wrongLogIn.setText(e.getMessage());
        }catch(WrongUsernameException e){
            wrongLogIn.setText(e.getMessage());
        }catch(WrongPasswordException e){
            wrongLogIn.setText(e.getMessage());
        }
    }

    //when register button is pressed
    public void userCreateNewAccount(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}