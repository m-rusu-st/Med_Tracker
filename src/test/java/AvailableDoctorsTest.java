import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class AvailableDoctorsTest {

    Parent root;

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();

        UserService.addUser("Matache", "Mircea", "0736789241", "Frunzei 9", "MatacheMircea", "matachemircea","Medic","cardiologie","spitalul judetean");
        UserService.addUser("Vulturu", "Maria", "0754567871", "Apicultorilor", "VulturuMaria", "vulturumaria", "Medic", "dermatologie", "DermaClinic");
        UserService.addUser("Stoian", "Ana", "0754567878", "Izlaz 10", "StoianAna", "stoianana", "Pacient", "", "");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void testAvailableDoctors(FxRobot robot) throws UsernameAlreadyExistsException, NoEmptyField {

        robot.clickOn("#usernameTextField");
        robot.write("StoianAna");
        robot.clickOn("#enterPasswordField");
        robot.write("stoianana");
        robot.clickOn("#logInButton");
        robot.clickOn("#availableDoctorsButton");

        assertEquals(robot.lookup("#productTableView").queryTableView().getColumns().get(0).getCellData(0).toString(),"Mircea Matache");
        assertEquals(robot.lookup("#productTableView").queryTableView().getColumns().get(0).getCellData(1).toString(),"Maria Vulturu");

    }

    @Test
    void testSearchBar(FxRobot robot){
        robot.clickOn("#usernameTextField");
        robot.write("StoianAna");
        robot.clickOn("#enterPasswordField");
        robot.write("stoianana");
        robot.clickOn("#logInButton");
        robot.clickOn("#availableDoctorsButton");
        robot.clickOn("#searchTextField");
        robot.write("vulturu");
        assertEquals(robot.lookup("#productTableView").queryTableView().getColumns().get(0).getCellData(0).toString(),"Maria Vulturu");

    }
}
