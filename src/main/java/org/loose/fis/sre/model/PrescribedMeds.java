package org.loose.fis.sre.model;

public class PrescribedMeds {
    String medicine;
    String dosage;
    String treatmentDuration;

    public PrescribedMeds(String medicine, String dosage, String treatmentDuration) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.treatmentDuration = treatmentDuration;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }
}
