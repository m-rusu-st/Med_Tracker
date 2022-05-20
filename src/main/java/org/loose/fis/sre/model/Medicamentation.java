package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.services.UserService;

import java.time.LocalDate;


public class Medicamentation {
    @Id
    String codUnic;

    String username;
    String medicamentation;
    String dosage;
    String endDate;
    String treatmentComplete;

    public static int contor;
    public Medicamentation(String username, String medicamentation, String dosage, String endDate, String treatmentComplete){
        this.username = username;
        this.medicamentation = medicamentation;
        this.dosage = dosage;
        this.endDate = endDate;
        this.treatmentComplete = treatmentComplete;

        contor = UserService.nrElementsMeds() + 1;
        codUnic = String.valueOf(contor);
    }

    public Medicamentation() {
    }

    public String getUsername(){return username;}

    public String getMedicamentation(){return medicamentation;}

    public void setMedicamentation(String medicamentation){this.medicamentation = medicamentation;}

    public String getDosage(){return dosage;}

    public void setDosage(String dosage){this.dosage = dosage;}

    public String getEndDate(){return endDate;}

    public void setEndDate(String endDate){this.endDate = endDate;}

    public String getTreatmentComplete(){return treatmentComplete;}

    public void setTreatmentComplete(String treatmentComplete){this.treatmentComplete = treatmentComplete;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicamentation user = (Medicamentation) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (medicamentation != null ? !medicamentation.equals(user.medicamentation) : user.medicamentation != null) return false;
        if (dosage != null ? !dosage.equals(user.dosage) : user.dosage != null) return false;
        if (endDate != null ? !endDate.equals(user.endDate) : user.endDate != null) return false;
        return  (treatmentComplete != null ? !treatmentComplete.equals(user.treatmentComplete) : user.treatmentComplete != null) ;
    }

}

