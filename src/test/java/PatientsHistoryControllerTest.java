import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class PatientsHistoryControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();
        UserService.addUser("Radoi", "Mirel", "0747851240", "Paltinisanu 22", "RadoiMirel", "radoimirel", "Medic", "Cardiology", "Judetean");
        UserService.addUser("Pop", "George", "0742258012", "Linistei 20", "PopGeorge", "popgeorge", "Pacient", "", "");
        UserService.addAppointment("Pop", "George", "0742258012", "PopGeorge", "5/25/2022", DayOfWeek.WEDNESDAY, "12:30", "RadoiMirel");
        UserService.addMedicamentation("PopGeorge", "Decasept", "3/day", "5/30/2022", "No!");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void checkHistory(FxRobot robot){
        robot.clickOn("#usernameTextField");
        robot.write("RadoiMirel");
        robot.clickOn("#enterPasswordField");
        robot.write("radoimirel");
        robot.clickOn("#logInButton");
        robot.clickOn("#patientHistoryButton");

        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(3).getCellData(0).toString(), "Decasept 3/day ");
    }
}
