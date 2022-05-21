package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.model.Appointment;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class DoctorAppointmentController {

    @FXML
    private ChoiceBox<String> selectAppointment;
    @FXML
    private ChoiceBox<String> validate;
    @FXML
    private Button saveButton;
    @FXML
    private Button back;
    @FXML
    private Label emptyfield;

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize() throws NoEmptyField {

            UserService.chooseAppointment(selectAppointment);
            validate.getItems().addAll("Yes!", "No!");

    }

    public void saveInfo(ActionEvent event) throws IOException, NoEmptyField
    {

        try {
            UserService.check2(selectAppointment.getValue());
            String[] param = ((String) selectAppointment.getValue()).split(" ");

            UserService.setAppointmentValidation(param[0], param[1], param[2], param[3], (String) validate.getValue());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }catch(NoEmptyField e){
            emptyfield.setText(e.getMessage());
        }
    }

    public void goBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

}

