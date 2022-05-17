package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class ChooseSpecialtyController {

    @FXML
    private TextField SpecialtyField;
    @FXML
    private TextField HospitalField;
    @FXML
    private Label emptyField;
    @FXML
    private Button Save;

    private Stage stage;
    private Parent root;
    private Scene scene;


    public void saveDetails(ActionEvent event) throws NoEmptyField, IOException {
        try {
            UserService.addUser2(SpecialtyField.getText(), HospitalField.getText());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (NoEmptyField e) {
            emptyField.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
