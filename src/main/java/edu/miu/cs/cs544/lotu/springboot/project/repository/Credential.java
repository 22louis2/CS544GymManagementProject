package edu.miu.cs.cs544.lotu.springboot.project.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("credentialRepository")
public interface Credential extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential, Long> {
    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Credential c WHERE c.username = :username")
    Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsernameLockedForUpdate(String username);
}
