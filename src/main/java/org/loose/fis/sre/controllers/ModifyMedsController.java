package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class ModifyMedsController {
    @FXML
    private ChoiceBox<String> patientsList;
    @FXML
    private ChoiceBox<String> medsChoiceBox;
    @FXML
    private TextField dosageTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> treatmentChoiceBox;
    @FXML
    private Button BackButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button DeleteButton;

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.populateChoiceBox(patientsList);
        //UserService.populateChoiceBox2(medsChoiceBox,(String) patientsList.getValue());
        treatmentChoiceBox.getItems().addAll("Da!", "Nu!");

    }

    public void doctorDeletesMed(ActionEvent e) throws IOException
    {

    }

    public void doctorSavesChanges(ActionEvent e) throws IOException
    {

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
