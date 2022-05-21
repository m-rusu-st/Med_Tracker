import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class MakeAnAppointmentTest {
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
        root = FXMLLoader.load(getClass().getClassLoader().getResource("MakeAnAppointment.fxml"));
        primaryStage.setTitle("Med Tracker");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}
