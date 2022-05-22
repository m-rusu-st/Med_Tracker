//package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.NoEmptyField;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationControllerTest {
    Parent root;

    public static final String LASTNAME = "Radoi";
    public static final String FIRSTNAME = "Mirel";
    public static final String PHONE = "0742587126";
    public static final String ADDRESS = "Paltinisanu 22";
    public static final String USERNAME = "RadoiMirel";
    public static final String PASSWORD = "radoimirel";
    public static final String SPECIALTY = "Cardiology";
    public static final String HOSPITAL = "Judetean";

    /*@BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }*/

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser("Pop", "George", "0741258639", "Linistei 21", "PopGeorge", "popgeorge", "Medic", "Oncology", "Clinic");


        root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) throws IOException, UsernameAlreadyExistsException, NoEmptyField {

        robot.clickOn("#LastNameField");
        robot.write(LASTNAME);
        robot.clickOn("#FirstNameField");
        robot.write(FIRSTNAME);
        robot.clickOn("#PhoneField");
        robot.write(PHONE);
        //robot.clickOn("#AddressField");
        //robot.write(ADDRESS);
        robot.clickOn("#UsernameField");
        robot.write(USERNAME);
        robot.clickOn("#PasswordField");
        robot.write(PASSWORD);
        robot.clickOn("#ChoiceBox");
        robot.clickOn("Medic");
        robot.clickOn("#Register");

        assertEquals(robot.lookup("#emptyField").queryLabeled().getText(), "Complete all fields!");
        robot.clickOn("#AddressField");
        robot.write(ADDRESS);

        robot.clickOn("#Register");

        Parent now = FXMLLoader.load(getClass().getClassLoader().getResource("ChooseSpecialty.fxml"));
        assertThat(root == now);


        robot.clickOn("#SpecialtyField");
        robot.write(SPECIALTY);
        robot.clickOn("#Save");

        assertEquals(robot.lookup("#emptyField").queryLabeled().getText(), "Complete all fields!");

        robot.clickOn("#HospitalField");
        robot.write(HOSPITAL);
        robot.clickOn("#Save");

        now = FXMLLoader.load(getClass().getClassLoader().getResource("Medic.fxml"));
        assertThat(root == now);
    }

    @Test
    void testRegistration2(FxRobot robot) throws IOException {
        robot.clickOn("#LastNameField");
        robot.write("Pop");
        robot.clickOn("#FirstNameField");
        robot.write("George");
        robot.clickOn("#PhoneField");
        robot.write("0741258639");
        robot.clickOn("#AddressField");
        robot.write("Linistei 21");
        robot.clickOn("#UsernameField");
        robot.write("PopGeorge");
        robot.clickOn("#PasswordField");
        robot.write("popgeorge");
        robot.clickOn("#ChoiceBox");
        robot.clickOn("Medic");
        robot.clickOn("#Register");

        assertEquals(robot.lookup("#emptyField").queryLabeled().getText(), (String.format("An account with the username PopGeorge already exists!")));
    }
}