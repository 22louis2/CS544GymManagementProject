package edu.miu.cs.cs544.lotu.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("subscriptionRepository")
public interface Subscription extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.Subscription, Long> {
}
