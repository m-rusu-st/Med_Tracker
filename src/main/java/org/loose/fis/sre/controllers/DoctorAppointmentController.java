package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.chooseAppointment(selectAppointment);
        validate.getItems().addAll("Yes!", "No!");

    }

    public void saveInfo(ActionEvent event) throws IOException
    {
        UserService.setAppointmentValidation((String) selectAppointment.getValue(), (String) validate.getValue());
    }

    public void userGoesToPreviousPageButton(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

}

