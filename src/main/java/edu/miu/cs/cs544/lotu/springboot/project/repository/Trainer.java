package edu.miu.cs.cs544.lotu.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trainerRepository")
public interface Trainer extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer, Long> {

    @Query("SELECT t FROM Trainer t WHERE (:specialization IS NULL OR t.specialization = :specialization) " +
            "AND (:minYearsOfExperience IS NULL OR t.yearsOfExperience >= :minYearsOfExperience) " +
            "AND (:qualification IS NULL OR t.qualification = :qualification)")
    List<edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer> findTrainersByDynamicCriteria(
            @Param("specialization") String specialization,
            @Param("minYearsOfExperience") Integer minYearsOfExperience,
            @Param("qualification") String qualification
    );
}
