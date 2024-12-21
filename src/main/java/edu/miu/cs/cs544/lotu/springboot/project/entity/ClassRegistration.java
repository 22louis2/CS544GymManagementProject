package edu.miu.cs.cs544.lotu.springboot.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ClassRegistration {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate registrationDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private GymClass gymClass;

    public ClassRegistration() {}

    public long getId() {
        return id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public GymClass getGymClass() {
        return gymClass;
    }

    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }

    @Override
    public String toString() {
        return "ClassRegistration{" +
                "id=" + id +
                ", registrationDate=" + registrationDate +
                ", member=" + member +
                ", gymClass=" + gymClass +
                '}';
    }
}
