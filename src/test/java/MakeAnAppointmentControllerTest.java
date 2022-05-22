import javafx.fxml.FXMLLoader;
import javafx.geometry.VerticalDirection;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class MakeAnAppointmentControllerTest {
    Parent root;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();
        UserService.addUser("Radoi", "Mirel", "0747851240", "Paltinisanu 22", "RadoiMirel", "radoimirel", "Medic", "Cardiology", "Judetean");

    }

    @Start
    void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 498, 698));
        primaryStage.show();
    }

    @Test
    void NoEmptyFields(FxRobot robot) throws IOException {
        robot.clickOn("#appointmentButton");
        robot.clickOn("#LastNameField");
        robot.write("Pop");
        robot.clickOn("#FirstNameField");
        robot.write("George");
        //robot.clickOn("#PhoneField");
        //robot.write("0741258639");
        robot.clickOn("#UsernameField");
        robot.write("PopGeorge");
        robot.clickOn("#datePicker");
        robot.write("5/24/2022");
        robot.clickOn("#timePicker");
        robot.scroll(15, VerticalDirection.DOWN);
        robot.clickOn("10:00");
        robot.clickOn("#doctorPicker");
        robot.clickOn("RadoiMirel Cardiology");
        robot.clickOn("#appointment");

        assertEquals(robot.lookup("#emptyField").queryLabeled().getText(), ("Complete all fields!"));
    }

    @Test
    void WrongDate(FxRobot robot) throws IOException, InterruptedException {
        robot.clickOn("#appointmentButton");
        robot.clickOn("#LastNameField");
        robot.write("Pop");
        robot.clickOn("#FirstNameField");
        robot.write("George");
        robot.clickOn("#PhoneField");
        robot.write("0741258639");
        robot.clickOn("#UsernameField");
        robot.write("PopGeorge");
        robot.clickOn("#datePicker");
        robot.moveTo(750, 400);
        robot.clickOn();
        robot.moveTo(750, 500);
        robot.clickOn();

        robot.clickOn("#timePicker");
        robot.scroll(15, VerticalDirection.DOWN);
        robot.clickOn("10:00");
        robot.clickOn("#doctorPicker");
        robot.clickOn("RadoiMirel Cardiology");
        robot.clickOn("#appointment");

        assertEquals(robot.lookup("#emptyField").queryLabeled().getText(), ("Wrong date and/or time!"));
    }

    @Test
    void saveAppointment(FxRobot robot) throws IOException, InterruptedException {
        robot.clickOn("#appointmentButton");
        robot.clickOn("#LastNameField");
        robot.write("Pop");
        robot.clickOn("#FirstNameField");
        robot.write("George");
        robot.clickOn("#PhoneField");
        robot.write("0741258639");
        robot.clickOn("#UsernameField");
        robot.write("PopGeorge");
        robot.clickOn("#datePicker");
        robot.moveTo(750, 400);
        robot.clickOn();
        robot.moveTo(700, 550);
        robot.clickOn();

        robot.clickOn("#timePicker");
        robot.scroll(15, VerticalDirection.DOWN);
        robot.clickOn("10:00");
        robot.clickOn("#doctorPicker");
        robot.clickOn("RadoiMirel Cardiology");
        robot.clickOn("#appointment");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        assertThat(root == now);
    }
}
