package edu.miu.cs.cs544.lotu.springboot.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( "trainerService")
public class Trainer {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.repository.Trainer trainerRepository;

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer> findTrainersByDynamicCriteria(String specialization, Integer minYearsOfExperience, String qualification) {
        return trainerRepository.findTrainersByDynamicCriteria(specialization, minYearsOfExperience, qualification);
    }
}
