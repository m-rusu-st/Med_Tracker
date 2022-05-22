import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.EmptyFieldsDoctorException;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class PrescribeMedsTest {
    Parent root;

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();

        UserService.addUser("Stoian","Ana","0737629012","Harniciei 9", "StoianAna","stoianana","Pacient", "","");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PrescribeMeds.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Modify meds");
        primaryStage.setScene(new Scene(root, 498, 698));
        primaryStage.show();
    }

    @Test
    void testException(FxRobot robot) throws EmptyFieldsDoctorException, NoEmptyField, UsernameAlreadyExistsException {
        robot.clickOn("#patientsList").clickOn("StoianAna");
        robot.clickOn("#addMedicineField");
        robot.write("Nurofen");
        robot.clickOn("#dosageField");
        robot.write("2/day");
        robot.clickOn("#treatmentChoiceBox").clickOn("No!");

        robot.clickOn("#saveButton");
        assertEquals(robot.lookup("#wrongField").queryLabeled().getText(),"Complete all fields!");
    }

    @Test
    void testPrescribeMeds(FxRobot robot) throws IOException {
        robot.clickOn("#patientsList").clickOn("StoianAna");
        robot.clickOn("#addMedicineField");
        robot.write("Nurofen");
        robot.clickOn("#dosageField");
        robot.write("2/day");
        robot.moveTo(925,485);
        robot.clickOn();
        robot.moveTo(800,750);
        robot.clickOn();
        robot.clickOn("#treatmentChoiceBox").clickOn("No!");

        robot.clickOn("#saveButton");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        Assertions.assertThat(root == now);
    }

    @Test
    void testBackButton(FxRobot robot) throws IOException {
        robot.clickOn("#BackButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        Assertions.assertThat(root == now);
    }

}
