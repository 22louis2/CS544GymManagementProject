package edu.miu.cs.cs544.lotu.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("workoutSessionRepository")
public interface WorkoutSession extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession, Long> {
    @Query("SELECT s FROM WorkoutSession s JOIN s.gymClass g JOIN g.trainer t WHERE t.id = :trainerId")
    List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> findSessionsByTrainer(@Param("trainerId") Long trainerId);
}
