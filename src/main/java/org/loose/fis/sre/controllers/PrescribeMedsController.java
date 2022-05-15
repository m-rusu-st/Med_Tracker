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
import org.dizitart.no2.NitriteCollection;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;
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

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initialize()
    {
        UserService.populateChoiceBox(patientsList);
        treatmentChoiceBox.getItems().addAll("Da!", "Nu!");

    }

    public void doctorSavesChanges(ActionEvent event) throws IOException
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