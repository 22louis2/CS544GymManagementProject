package edu.miu.cs.cs544.lotu.springboot.project.specification;


import edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass;
import edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ClassRegistration {
    public static Specification<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> hasRegistrationDateAfter(LocalDate date) {
        return (root, query, builder) -> builder.greaterThan(root.get("registrationDate"), date);
    }

    public static Specification<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> hasGymClassWithCapacityGreaterThan(int capacity) {
        return (root, query, builder) -> {
            Join<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration, GymClass> gymClass = root.join("gymClass");
            return builder.greaterThan(gymClass.get("capacity"), capacity);
        };
    }

    public static Specification<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> hasGymClassWithTrainerHavingExperienceMoreThan(int years) {
        return (root, query, builder) -> {
            Join<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration, GymClass> gymClass = root.join("gymClass"); // Join with GymClass
            Join<GymClass, Trainer> trainer = gymClass.join("trainer"); // Join with Trainer via GymClass
            return builder.greaterThan(trainer.get("yearsOfExperience"), years);
        };
    }
}
