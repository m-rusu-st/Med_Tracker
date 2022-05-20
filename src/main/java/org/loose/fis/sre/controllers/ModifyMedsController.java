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
import org.loose.fis.sre.model.Medicamentation;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ModifyMedsController {
    @FXML
    private ChoiceBox<String> patientsList;
    @FXML
    private TextField meds;
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

    public void doctorDeletesMed(ActionEvent e) throws NoEmptyField, NoMedicineException
    {
        try {
            UserService.check(patientsList.getValue(), datePicker.getValue());
            Medicamentation medicine = new Medicamentation((String) patientsList.getValue(), meds.getText(), dosageTextField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (String) treatmentChoiceBox.getValue());
            UserService.deleteMedicamentation(medicine);

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }catch(NoEmptyField event){
            wrongField.setText(event.getMessage());
        }catch(NoMedicineException event){
            wrongField.setText(event.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void doctorSavesChanges(ActionEvent e) throws IOException, NoEmptyField
    {
        try{
            UserService.check(patientsList.getValue(), datePicker.getValue());
            UserService.modifyMedicamentation((String)patientsList.getValue(), meds.getText(), dosageTextField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (String)treatmentChoiceBox.getValue());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }catch(NoEmptyField event){
            wrongField.setText(event.getMessage());
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
