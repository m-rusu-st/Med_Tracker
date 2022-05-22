import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.model.Medicamentation;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile2;

@ExtendWith(ApplicationExtension.class)
public class ModifyMedsTest {
    Parent root;

    public static final String LASTNAME = "Stoian";
    public static final String FIRSTNAME = "Ana";
    public static final String PHONE = "0737629012";
    public static final String ADDRESS = "Harniciei 9";
    public static final String USERNAME = "StoianAna";
    public static final String PASSWORD = "stoianana";
    public static final String ROLE = "Pacient";
    public static final String MEDICINE = "Decasept";
    public static String DOSAGE = "2/day";
    public static String DATE = "6/1/2022";
    public static String TREATMENTCOMPLETE = "No!";

    @Start
    void start(Stage primaryStage) throws Exception {
         FileSystemService.APPLICATION_FOLDER = ".registration-example";
         FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
         UserService.initDatabase();
         UserService.initDatabase2();

         UserService.addUser(LASTNAME,FIRSTNAME,PHONE,ADDRESS, USERNAME,PASSWORD,ROLE, "","");
         UserService.addMedicamentation(USERNAME,MEDICINE, DOSAGE,DATE, TREATMENTCOMPLETE);

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyMeds.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Modify meds");
        primaryStage.setScene(new Scene(root, 498, 698));
        primaryStage.show();
    }

    @Test
    void testCompleteAllFieldsException(FxRobot robot) throws NoEmptyField{
        robot.clickOn("#meds");
        robot.write("Decasept");
        robot.clickOn("#dosageTextField");
        robot.write("3/day");
        robot.clickOn("#datePicker");
        robot.write("5/26/2022");
        robot.clickOn("#treatmentChoiceBox").clickOn("No!");
        robot.clickOn("#saveButton");

        assertEquals(robot.lookup("#wrongField").queryLabeled().getText(),"Complete all fields!");
    }

    @Test
    void testModifyMeds(FxRobot robot) throws IOException, NoEmptyField{
        robot.clickOn("#patientsList").clickOn("StoianAna");
        robot.clickOn("#meds");
        robot.write("Decasept");
        robot.clickOn("#dosageTextField");
        robot.write("3/day");
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
    void testDeleteMeds(FxRobot robot) throws IOException {
        robot.clickOn("#patientsList").clickOn("StoianAna");
        robot.clickOn("#meds");
        robot.write("Decasept");
        robot.clickOn("#dosageTextField");
        robot.write("3/day");
        robot.moveTo(925,485);
        robot.clickOn();
        robot.moveTo(800,750);
        robot.clickOn();
        robot.clickOn("#treatmentChoiceBox").clickOn("No!");
        robot.clickOn("#DeleteButton");

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
