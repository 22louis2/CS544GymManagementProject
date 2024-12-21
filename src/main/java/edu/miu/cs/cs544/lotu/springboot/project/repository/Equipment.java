package edu.miu.cs.cs544.lotu.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("equipmentRepository")
public interface Equipment extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment, Long> {
}
