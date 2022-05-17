package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;

public class PatientController {
    @FXML
    private Button availableDoctorsButton;
    @FXML
    private Button appointmentButton;
    @FXML
    private Button prescribedMedsButton;
    @FXML
    private Button doctorHistoryButton;
    @FXML
    private Button logOutButton;

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void seeAvailableDoctors(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AvailableDoctors.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

    public void userMakesAnAppointment(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Appointment.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

    public void seePrescribedMeds(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PatientPrescribedMeds.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

    public void seeDoctorHistory(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DoctorHistory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }

    public void patientLogsOut(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LogOut.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }


}
