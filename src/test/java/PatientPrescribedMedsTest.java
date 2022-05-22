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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class PatientPrescribedMedsTest {
    Parent root;

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.initDatabase2();
        UserService.initDatabase3();

        UserService.addUser("Stoian", "Ana", "0754567878", "Izlaz 10", "StoianAna", "stoianana", "Pacient", "", "");
        UserService.addMedicamentation("StoianAna", "Decasept", "3/day", "27/5/2022", "No!");
        UserService.addMedicamentation("StoianAna", "Nurofen", "2/day", "30/5/2022", "No!");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void testPrescribedMeds(FxRobot robot){
        robot.clickOn("#usernameTextField");
        robot.write("StoianAna");
        robot.clickOn("#enterPasswordField");
        robot.write("stoianana");
        robot.clickOn("#logInButton");
        robot.clickOn("#prescribedMedsButton");

        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(0).getCellData(0).toString(),"Decasept");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(1).getCellData(0).toString(),"3/day");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(2).getCellData(0).toString(),"27/5/2022");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(0).getCellData(1).toString(),"Nurofen");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(1).getCellData(1).toString(),"2/day");
        assertEquals(robot.lookup("#tableView").queryTableView().getColumns().get(2).getCellData(1).toString(),"30/5/2022");

    }
}
