package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.loose.fis.sre.services.UserService;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MakeAnAppointmentController {

    @FXML
    private TextField LastNameField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField UsernameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> timePicker;
    @FXML
    private ChoiceBox<String> doctorPicker;
    @FXML
    private Button appointment;
    @FXML
    private Button Back;
    @FXML
    private Label emptyField;

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.populateChoiceBox2(timePicker);
        UserService.populateChoiceBox3(doctorPicker);
    }

    public void makeAppointment(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) {
    }
}
