package org.loose.fis.sre.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.ProductSearch;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientPrescribedMedsController implements Initializable{

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn MedicinesTableColumn;
    @FXML
    private TableColumn DosageTableColumn;
    @FXML
    private TableColumn TreatmentDurationTableColumn;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resource){

    }

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void userGoesToPreviousPage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }
}
