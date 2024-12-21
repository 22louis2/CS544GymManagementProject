package edu.miu.cs.cs544.lotu.springboot.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class WorkoutSession {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;

    @ManyToOne
    private Member member;

    @ManyToOne
    private GymClass gymClass;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL)
    private List<Equipment> equipment;

    public WorkoutSession() {}

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "WorkoutSession{" +
                "id=" + id +
                ", date=" + date +
                ", member=" + member +
                ", gymClass=" + gymClass +
                ", equipment=" + equipment +
                '}';
    }
}
