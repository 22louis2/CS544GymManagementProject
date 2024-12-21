package edu.miu.cs.cs544.lotu.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("gymClassRepository")
public interface GymClass extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass, Long> {
}
