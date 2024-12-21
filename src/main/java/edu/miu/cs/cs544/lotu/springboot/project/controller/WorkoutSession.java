package edu.miu.cs.cs544.lotu.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("workoutSessionController")
@RequestMapping("/api/workout-sessions")
public class WorkoutSession {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.service.WorkoutSession workoutSessionService;

    @GetMapping
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession>> getAllWorkoutSessions() {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> workoutSessions = workoutSessionService.getAllWorkoutSessions();
        return ResponseEntity.ok(workoutSessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> getWorkoutSessionById(@PathVariable Long id) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> workoutSession = workoutSessionService.getWorkoutSessionById(id);
        return workoutSession
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> createWorkoutSession(@RequestBody edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession workoutSession) {
        edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession createdWorkoutSession = workoutSessionService.createWorkoutSession(workoutSession);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> updateWorkoutSession(@PathVariable Long id, @RequestBody edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession updatedWorkoutSession) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> workoutSession = workoutSessionService.updateWorkoutSession(id, updatedWorkoutSession);
        return workoutSession
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSession(@PathVariable Long id) {
        if (workoutSessionService.deleteWorkoutSession(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession>> findSessionsByTrainer(@PathVariable Long trainerId) {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession> sessions = workoutSessionService.findSessionsByTrainer(trainerId);
        if (sessions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sessions);
    }
}
