package edu.miu.cs.cs544.lotu.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("trainerController")
@RequestMapping("/api/trainers")
public class Trainer {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.service.Trainer trainerService;

    @GetMapping
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer>> findTrainers(@RequestParam(required = false) String specialization,
                    @RequestParam(required = false) Integer minYearsOfExperience, @RequestParam(required = false) String qualification) {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer> trainers = trainerService.findTrainersByDynamicCriteria(specialization, minYearsOfExperience, qualification);
        if (trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }
}
