package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientsHistoryController implements Initializable {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn LastNameTableColumn;
    @FXML
    private TableColumn FirstNameTableColumn;
    @FXML
    private TableColumn PhoneTableColumn;
    @FXML
    private TableColumn MedsTableColumn;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resource){
        LastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        FirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        PhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        MedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("medicamentation"));

        UserService.populateTableView4(tableView);
    }

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void userGoesToPreviousPage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); //interface will appear in the center of the screen
        stage.show();
    }
}
