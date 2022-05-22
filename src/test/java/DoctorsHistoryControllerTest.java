import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javax.validation.constraints.Null;
import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class DoctorsHistoryControllerTest {

   /* @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();
        UserService.addUser("Radoi", "Mirel", "0747851240", "Paltinisanu 22", "RadoiMirel", "radoimirel", "Medic", "Cardiology", "Judetean");
        UserService.addUser("Pop", "George", "0742258012", "Linistei 20", "PopGeorge", "popgeorge", "Pacient", "", "");
        UserService.addAppointment("Pop", "George", "0742258012", "PopGeorge", "5/25/2022", DayOfWeek.WEDNESDAY, "12:30", "RadoiMirel");

    }*/

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


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void checkHistory(FxRobot robot){
        robot.clickOn("#usernameTextField");
        robot.write("PopGeorge");
        robot.clickOn("#enterPasswordField");
        robot.write("popgeorge");
        robot.clickOn("#logInButton");
        robot.clickOn("#doctorHistoryButton");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(0).getCellData(0).toString(), "Radoi");
    }


}
