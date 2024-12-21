package edu.miu.cs.cs544.lotu.springboot.project.entity;

import jakarta.persistence.Entity;

@Entity
public class Trainer extends Person {
    private String specialization;
    private String bio;
    private int yearsOfExperience;
    private String qualification;

    public Trainer() {}

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "specialization='" + specialization + '\'' +
                ", bio='" + bio + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}
