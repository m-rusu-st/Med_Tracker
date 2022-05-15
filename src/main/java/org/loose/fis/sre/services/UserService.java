package org.loose.fis.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.Medicamentation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile2;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static ObjectRepository<Medicamentation> userRepository2;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("MedTracker.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void initDatabase2() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile2("MedTracker2.db").toFile())
                .openOrCreate("test", "test");

        userRepository2 = database.getRepository(Medicamentation.class);
    }

    public static int addUser(String LastName, String FirstName, String phone, String address, String username, String password, String role) throws UsernameAlreadyExistsException, NoEmptyField {
        checkUserDoesNotAlreadyExist(username);
        if(LastName.equals("") || FirstName.equals("") || phone.equals("") || address.equals("") || username.equals("") || password.equals("") || role.equals("")) throw new NoEmptyField();

        userRepository.insert(new User(LastName, FirstName, phone, address, username, encodePassword(username, password), role));

        if(role.equals("Pacient"))return 1;
        else if(role.equals("Medic"))return 2;

        return 0;
    }

    public static int addMedicamentation(String username, String medicamentation, String dosage, LocalDate date, String treatmentComplete) throws EmptyFieldsDoctorException {

        if(medicamentation.equals("") || dosage.equals("") || date.equals("") || treatmentComplete.equals(""))throw new EmptyFieldsDoctorException();
        userRepository2.insert(new Medicamentation(username, medicamentation, dosage, date, treatmentComplete));

        return 0;
    }


    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    //method that checks credentials in Log In
    public static int checkCredentials(String username, String password) throws WrongUsernameException, WrongPasswordException, EmptyUsernameFieldException, EmptyPasswordFieldException, EmptyUsernamePasswordFieldException{
        int v_username = 0, v_password = 0; // initial the username and password does not exist in the database
        String encryptedPassword = encodePassword(username, password);

        if(username.equals("") && password.equals("")) throw new EmptyUsernamePasswordFieldException();
        else if(username.equals("")) throw new EmptyUsernameFieldException();
        else if(password.equals("")) throw new EmptyPasswordFieldException();

        for(User user : userRepository.find())
        {
            if(Objects.equals(username, user.getUsername())){
                v_username = 1; //username exists
                if(Objects.equals(encryptedPassword, user.getPassword())){
                    v_password = 1; // password exists

                    if (user.getRole().equals("Pacient")) return 1;
                    else if (user.getRole().equals("Medic")) return 2;
                }
            }
        }

        if(v_username == 0) throw new WrongUsernameException(username);
        if(v_password == 0) throw new WrongPasswordException();

        return 0;
    }

    //method that takes data from the database and adds it to the choiceBox (MEDIC!!)
    public static void populateChoiceBox(ChoiceBox x){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(User user : userRepository.find())
        {
            if(Objects.equals("Pacient", user.getRole())){
                list.add(user.getUsername());
            }
        }

        x.setItems(list);
    }

    public static void populateChoiceBox2(ChoiceBox x, String username){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Medicamentation user : userRepository2.find())
        {
            if(Objects.equals(username, user.getUsername()))
                list.add(user.getUsername() +" " + user.getMedicamentation() + " " + user.getDosage() + " " + user.getEndDate());
        }

        x.setItems(list);
    }

}
