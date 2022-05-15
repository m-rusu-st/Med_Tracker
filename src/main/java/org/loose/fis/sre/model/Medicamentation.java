package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.time.LocalDate;


public class Medicamentation {
    @Id
    String username;
    String medicamentation;
    String dosage;
    LocalDate endDate;
    String treatmentComplete;

    public Medicamentation(String username, String medicamentation, String dosage, LocalDate endDate, String treatmentComplete){
        this.username = username;
        this.medicamentation = medicamentation;
        this.dosage = dosage;
        this.endDate = endDate;
        this.treatmentComplete = treatmentComplete;
    }

    public Medicamentation() {
    }
    public String getUsername(){return username;}

    public String getMedicamentation(){return medicamentation;}

    public void setMedicamentation(String medicamentation){this.medicamentation = medicamentation;}

    public String getDosage(){return dosage;}

    public LocalDate getEndDate(){return endDate;}

    public String getTreatmentComplete(){return treatmentComplete;}

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

