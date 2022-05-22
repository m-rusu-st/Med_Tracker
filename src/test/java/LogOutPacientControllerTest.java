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

import java.io.IOException;
import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class LogOutPacientControllerTest {
    Parent root;

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();

        UserService.addUser("Pop", "George", "0742258012", "Linistei 20", "PopGeorge", "popgeorge", "Pacient", "", "");

        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void pacientLogsOut(FxRobot robot) throws IOException {

        robot.clickOn("#usernameTextField");
        robot.write("PopGeorge");
        robot.clickOn("#enterPasswordField");
        robot.write("popgeorge");
        robot.clickOn("#logInButton");
        robot.clickOn("#logOutButton");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("LogOutPacient.fxml"));
        assertThat(root == now);

        robot.clickOn("#logOutButton");
    }
}
