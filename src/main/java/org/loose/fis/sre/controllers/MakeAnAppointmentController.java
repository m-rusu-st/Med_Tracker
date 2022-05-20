package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.exceptions.AppointmentError;
import org.loose.fis.sre.services.UserService;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
    private Label emptyField1;
    @FXML
    private Label emptyField2;

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.populateChoiceBox2(timePicker);
        UserService.populateChoiceBox3(doctorPicker);
    }

    public void makeAppointment(ActionEvent event) throws IOException, NoEmptyField, AppointmentError {
        try{
          UserService.check(doctorPicker.getValue(), datePicker.getValue());
          String[]  param = ((String) doctorPicker.getValue()).split(" ");
          UserService.addAppointment(LastNameField.getText(), FirstNameField.getText(), PhoneField.getText(), UsernameField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (String) timePicker.getValue(), param[0]);

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

       }catch(NoEmptyField e){
            emptyField1.setText(e.getMessage());
        }catch (AppointmentError e){
            emptyField2.setText(e.getMessage());
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }
}
