package org.loose.fis.sre.services;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

//import static org.loose.fis.sre.services.FileSystemService.*;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile2;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile3;


public class UserService {

    private static ObjectRepository<User> userRepository;
    private static ObjectRepository<Medicamentation> userRepository2;
    private static ObjectRepository<Appointment> userRepository3;

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

    public static void initDatabase3() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile3("MedTrackerAppointments.db").toFile())
                .openOrCreate("test", "test");

        userRepository3 = database.getRepository(Appointment.class);
    }

    private static String u;
    public static int addUser(String LastName, String FirstName, String phone, String address, String username, String password, String role, String specialty, String clinic_hospital) throws UsernameAlreadyExistsException, NoEmptyField {
        checkUserDoesNotAlreadyExist(username);
        if(LastName.equals("") || FirstName.equals("") || phone.equals("") || address.equals("") || username.equals("") || password.equals("") || role.equals("")) throw new NoEmptyField();

        userRepository.insert(new User(LastName, FirstName, phone, address, username, encodePassword(username, password), role, specialty, clinic_hospital));

        if(role.equals("Pacient")){
            return 1;
        }
        else if(role.equals("Medic")){
            u = username;
            return 2;
        }

        return 0;
    }

    public static void addUser2(String specialty, String hospital) throws NoEmptyField{
        for(User user : userRepository.find())
        {
            if(Objects.equals(u, user.getUsername()))
            {
                if(Objects.equals(specialty, "") || Objects.equals(hospital, "")) throw new NoEmptyField();
                else{
                     user.setSpecialty(specialty);
                     user.setClinic_hospital(hospital);
                     userRepository.update(user);
                }

            }
        }
    }


    public static int addMedicamentation(String username, String medicamentation, String dosage, String date, String treatmentComplete) throws EmptyFieldsDoctorException {

        if(medicamentation.equals("") || dosage.equals("") || date.equals("") || treatmentComplete.equals(""))throw new EmptyFieldsDoctorException();
        userRepository2.insert(new Medicamentation(username, medicamentation, dosage, date, treatmentComplete));

        return 0;
    }

    public static void deleteMedicamentation(Medicamentation meds)
    {
        userRepository2.remove(meds);
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

    private static String m,p;
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

                    if (user.getRole().equals("Pacient")) { p = user.getUsername(); return 1;}
                    else if (user.getRole().equals("Medic")){ m = user.getUsername();  return 2; }
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

    //method that takes data from the appointment database and adds it to the choiceBox
    public static void populateChoiceBox2(ChoiceBox x) {
        ObservableList<String> list = FXCollections.observableArrayList();
        int h1, h2, m1, m2;
        for(h1=0; h1<=2; h1=h1+1)
            for(h2=0; h2<=9; h2=h2+1)
                for(m1=0; m1<=5; m1=m1+1)
                    for(m2=0; m2<=9; m2=m2+10)
                        if(h1==2 && h2>3) break;
                        else
                            list.add(h1 + "" + h2 + ":" + m1 + m2);

        x.setItems(list);
    }

    public static void populateChoiceBox3(ChoiceBox x){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(User user : userRepository.find())
        {
            if(Objects.equals("Medic", user.getRole())){
                list.add(user.getUsername() + " " + user.getSpecialty());
            }
        }
        x.setItems(list);
    }


    //method that takes data from the database and adds it to the tableView
    public static void populateTableView(TableView x, TextField y){
        ObservableList<ProductSearch> list = FXCollections.observableArrayList();
        for(User user : userRepository.find())
        {
            if(Objects.equals("Medic", user.getRole())){
                String name = user.getFirstName() + " " + user.getLastName();
                String specialty = user.getSpecialty();
                String clinic = user.getClinic_hospital();
                list.add(new ProductSearch(name,specialty,clinic));
            }
        }

        x.setItems(list);

        //PT search
        FilteredList<ProductSearch> filteredData = new FilteredList<>(list, b->true);
        y.textProperty().addListener((observable, oldValue,newValue) -> {
            filteredData.setPredicate(ProductSearch -> {

                //if no search value then display all records or whatever records it current have no changes
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword= newValue.toLowerCase();

                if(ProductSearch.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true; //Means we found a match in Name
                }else if(ProductSearch.getSpecialty().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; //Means we found a match in specialty
                }else if(ProductSearch.getClinic().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; //Means we found a match in clinic
                }else
                    return false; //no match found

            });
        });

        SortedList<ProductSearch> sortedData = new SortedList <>(filteredData);
        //Bind sorted result with Table View
        sortedData.comparatorProperty().bind(x.comparatorProperty());

        //Apply filtered and sorted data to the Table View
        x.setItems(sortedData);

    }

    public static void populateTableView2(TableView x){
        ObservableList<PrescribedMeds> list = FXCollections.observableArrayList();
        for(Medicamentation medicamentation : userRepository2.find()){
            if(Objects.equals(p,medicamentation.getUsername())){
                String medicine = medicamentation.getMedicamentation();
                String dosage = medicamentation.getDosage();
                String treatmentDuration = medicamentation.getEndDate();
                list.add(new PrescribedMeds(medicine,dosage,treatmentDuration));
            }
        }
        x.setItems(list);
    }

    //Objects.equals(newDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    public static void modifyMedicamentation(String username, String med, String newDosage, String newDate, String treatmentComplete) throws NoEmptyField
    {
        for(Medicamentation medicamentation : userRepository2.find())
        {
            if(Objects.equals(username, medicamentation.getUsername()) && Objects.equals(med, medicamentation.getMedicamentation()))
            {
                if(Objects.equals(newDosage, "") || Objects.equals(treatmentComplete, "")) throw new NoEmptyField();
                else{
                     medicamentation.setDosage(newDosage);
                     medicamentation.setEndDate(newDate);
                     medicamentation.setTreatmentComplete(treatmentComplete);
                     userRepository2.update(medicamentation);
                }

            }
        }
    }

    public static int check(String cb1, LocalDate cb2) throws NoEmptyField{
        if(Objects.equals(cb1, "") ||  (cb2 == null)) throw new NoEmptyField();

        return 0;
    }

    public static int check2(String cb1) throws NoEmptyField{
        if(Objects.equals(cb1, "")) throw new NoEmptyField();

        return 0;
    }

    public static int addAppointment(String LastName, String FirstName, String phone, String username, String date, String time, String doctor) throws NoEmptyField, AppointmentError {

        if(LastName.equals("") || FirstName.equals("") || phone.equals("") || username.equals("") || date.equals("") || time.equals("") || doctor.equals(""))throw new NoEmptyField();
        else if(!LastName.equals("") && !FirstName.equals("") && !phone.equals("") && !username.equals("") && !date.equals("") && !time.equals("") && !doctor.equals("") && (time.compareTo("08:00")<0 || time.compareTo("17:00")>0)) throw new AppointmentError();
        else
        userRepository3.insert(new Appointment(username, LastName, FirstName, phone, date, time, doctor, ""));
        return 0;
    }


    //method that takes data from the appointment database and adds it to the choiceBox
    public static void chooseAppointment(ChoiceBox x) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Appointment appointment : userRepository3.find()) {
            if(appointment.getDoctor().equals(m))
            list.add(appointment.getUsername() + " " + appointment.getLastName() + " " + appointment.getFirstName() + " " + appointment.getDate() + " " + appointment.getTime() + " " + appointment.getPhone() + " " + appointment.getValid());
        }

        x.setItems(list);
    }

    public static void setAppointmentValidation(String username, String valid)
    {
        for(Appointment appointment : userRepository3.find())
        {
            if(Objects.equals(username, appointment.getUsername()))
            {
                appointment.setValid(valid);
                userRepository3.update(appointment);
            }
        }
    }

}
