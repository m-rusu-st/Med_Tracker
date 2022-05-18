package org.loose.fis.sre.model;

public class ProductSearch {
    String name;
    String specialty;
    String clinic;

    public ProductSearch(String name, String specialty, String clinic) {
        this.name = name;
        this.specialty = specialty;
        this.clinic = clinic;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getClinic() {
        return clinic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
