import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class AppointmentDetailsTest {
    Parent root;

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();

        UserService.addUser("Matache", "Mircea", "0736789241", "Frunzei 9", "MatacheMircea", "matachemircea","Medic","cardiologie","spitalul judetean");
        UserService.addUser("Stoian", "Ana", "0754567878", "Izlaz 10", "StoianAna", "stoianana", "Pacient", "", "");
        UserService.addAppointment("Stoian", "Ana","0754567878", "StoianAna","26/05/2022", DayOfWeek.THURSDAY,"12:00","MatacheMircea");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void testValidare(FxRobot robot){
        robot.clickOn("#usernameTextField");
        robot.write("StoianAna");
        robot.clickOn("#enterPasswordField");
        robot.write("stoianana");
        robot.clickOn("#logInButton");
        robot.clickOn("#appointmentDetailsButton");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(0).getCellData(0).toString(),"Ana Stoian");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(1).getCellData(0).toString(),"12:00");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(2).getCellData(0).toString(),"26/05/2022");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(3).getCellData(0).toString(),"Mircea Matache, spitalul judetean");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(4).getCellData(0).toString(),"Processing");

    }
}
