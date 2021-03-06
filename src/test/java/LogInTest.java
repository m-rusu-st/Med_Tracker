import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
//import org.h2.store.fs.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class LogInTest {
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    @DisplayName("Empty username and password field")
    void testLogInEmptyUsernamePassword(FxRobot robot) throws EmptyUsernamePasswordFieldException,UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "medic", "dermatologie", "dermaClinic");
        robot.clickOn("#logInButton");

        assertEquals(robot.lookup("#wrongLogIn").queryLabeled().getText(),"Please enter your data!");
    }

    @Test
    @DisplayName("Empty username field")
    void testLogInEmptyUsername(FxRobot robot) throws EmptyUsernameFieldException,UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "medic", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("");
        robot.clickOn("#enterPasswordField");
        robot.write("matachemircea");
        robot.clickOn("#logInButton");

        assertEquals(robot.lookup("#wrongLogIn").queryLabeled().getText(),"Please enter your username!");
     }

    @Test
    @DisplayName("Empty password field")
    void testLogInEmptyPassword(FxRobot robot) throws EmptyPasswordFieldException, UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "medic", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("MatacheMircea");
        robot.clickOn("#enterPasswordField");
        robot.write("");
        robot.clickOn("#logInButton");
        assertEquals(robot.lookup("#wrongLogIn").queryLabeled().getText(),"Please enter your password!");
    }

    @Test
    @DisplayName("Wrong password")
    void testWrongPassword(FxRobot robot) throws WrongPasswordException, UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "medic", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("MatacheMircea");
        robot.clickOn("#enterPasswordField");
        robot.write("parola");
        robot.clickOn("#logInButton");
        assertEquals(robot.lookup("#wrongLogIn").queryLabeled().getText(),"Wrong password! Try again!");
    }

    @Test
    @DisplayName("Wrong username")
    void testWrongUsername(FxRobot robot) throws WrongUsernameException,UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "medic", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("Matachemircea");
        robot.clickOn("#enterPasswordField");
        robot.write("matachemircea");
        robot.clickOn("#logInButton");
        assertEquals(robot.lookup("#wrongLogIn").queryLabeled().getText(),String.format("An account with the username %s does not exists!" + "\n" + "Try again!", "Matachemircea"));
    }

    @Test
    @DisplayName("User logged in as doctor successfully")
    void testLoginDoctor(FxRobot robot) throws UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "Medic", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("MatacheMircea");
        robot.clickOn("#enterPasswordField");
        robot.write("matachemircea");
        robot.clickOn("#logInButton");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        assertThat(root == now);
    }

    @Test
    @DisplayName("User logged in as patient successfully")
    void testLoginPatient(FxRobot robot) throws UsernameAlreadyExistsException, NoEmptyField, IOException {
        UserService.addUser("Matache", "Mircea", "0756754341", "Soarelui", "MatacheMircea", "matachemircea", "Pacient", "dermatologie", "dermaClinic");

        robot.clickOn("#usernameTextField");
        robot.write("MatacheMircea");
        robot.clickOn("#enterPasswordField");
        robot.write("matachemircea");
        robot.clickOn("#logInButton");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        assertThat(root == now);
    }

    @Test
    @DisplayName("User goes to register page")
    void testregister (FxRobot robot) throws IOException {
        robot.clickOn("#createNewAccountButton");
        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        assertThat(root == now);
    }

}
