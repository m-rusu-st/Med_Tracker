import com.sun.glass.ui.ClipboardAssistance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class MedicTest {
    Parent root;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 498, 698));
        primaryStage.show();
    }

    @Test
    void testManageAppointmentButton(FxRobot robot) throws IOException {
       robot.clickOn("#manageAppointmentsButton");
       Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("DoctorAppointments.fxml"));
       assertThat(root == now);

    }

    @Test
    void testPrescribeMedsButton(FxRobot robot) throws IOException {
        robot.clickOn("#prescribeMedsButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("PrescribeMeds.fxml"));
        assertThat(root == now);
    }

    @Test
    void testModifyMedsButton(FxRobot robot) throws IOException {
        robot.clickOn("#modifyMedsButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyMeds.fxml"));
        assertThat(root == now);
    }

    @Test
    void testPatientsHistoryButton(FxRobot robot) throws IOException {
        robot.clickOn("#patientHistoryButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("PatientsHistory.fxml"));
        assertThat(root == now);
    }

    @Test
    void testLogOutButton(FxRobot robot) throws IOException {
        robot.clickOn("#logOutButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("LogOutMedic.fxml"));
        assertThat(root == now);
        robot.clickOn("#logOutButton");
    }
}
