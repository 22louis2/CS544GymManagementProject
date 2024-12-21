package edu.miu.cs.cs544.lotu.springboot.project.repository;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("classRegistrationRepository")
public interface ClassRegistration extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration, Long>,
        JpaSpecificationExecutor<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> {
    @Query("SELECT cr FROM ClassRegistration cr JOIN cr.member m " +
            "JOIN m.subscription s WHERE s.packageType = :packageType " +
            "AND cr.registrationDate BETWEEN :startDate AND :endDate")
    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> findRegistrationsByPackageTypeAndDateRange(
            @Param("packageType") PackageType packageType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
