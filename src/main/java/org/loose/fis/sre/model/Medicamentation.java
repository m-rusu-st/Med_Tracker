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

    public String getDate(){return medicamentation;}

    public LocalDate getEndDate(){return endDate;}

    public String getTreatmentComplete(){return treatmentComplete;}
}

