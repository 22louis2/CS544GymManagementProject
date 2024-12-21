package edu.miu.cs.cs544.lotu.springboot.project.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service( "workoutSessionService")
public class WorkoutSession {
    private edu.miu.cs.cs544.lotu.springboot.project.repository.WorkoutSession workoutSessionRepository;

    public WorkoutSession() {}

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionRepository.findAll();
    }

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> getWorkoutSessionById(Long id) {
        return workoutSessionRepository.findById(id);
    }

    public edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession createWorkoutSession(edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession workoutSession) {
        return workoutSessionRepository.save(workoutSession);
    }

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> updateWorkoutSession(Long id, edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession updatedWorkoutSession) {
        return workoutSessionRepository.findById(id)
                .map(session -> {
                    session.setDate(updatedWorkoutSession.getDate());
                    session.setMember(updatedWorkoutSession.getMember());
                    session.setGymClass(updatedWorkoutSession.getGymClass());
                    session.setEquipment(updatedWorkoutSession.getEquipment());
                    return workoutSessionRepository.save(session);
                });
    }

    public boolean deleteWorkoutSession(Long id) {
        if (workoutSessionRepository.existsById(id)) {
            workoutSessionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> findSessionsByTrainer(Long trainerId) {
        return workoutSessionRepository.findSessionsByTrainer(trainerId);
    }
}
