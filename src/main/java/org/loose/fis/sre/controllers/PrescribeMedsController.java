package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
//import org.dizitart.no2.NitriteCollection;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class PrescribeMedsController {
    @FXML
    private ChoiceBox<String> patientsList;
    @FXML
    private TextField addMedicineField;
    @FXML
    private TextField dosageField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> treatmentChoiceBox;
    @FXML
    private Button BackButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label wrongField;


    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.populateChoiceBox(patientsList);
        treatmentChoiceBox.getItems().addAll("Yes!", "No!");

    }

    public void doctorSavesChanges(ActionEvent event) throws EmptyFieldsDoctorException, NoEmptyField
    {
        try{
            UserService.check(patientsList.getValue(), datePicker.getValue());
            UserService.addMedicamentation((String) patientsList.getValue(), addMedicineField.getText(), dosageField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (String)treatmentChoiceBox.getValue());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }catch(EmptyFieldsDoctorException e){
            wrongField.setText(e.getMessage());
        }catch(NoEmptyField e){
            wrongField.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

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