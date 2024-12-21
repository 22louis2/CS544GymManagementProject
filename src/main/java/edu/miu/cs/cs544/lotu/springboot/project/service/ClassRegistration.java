package edu.miu.cs.cs544.lotu.springboot.project.service;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service( "classRegistrationService")
public class ClassRegistration {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.repository.ClassRegistration classRegistrationRepository;

    public ClassRegistration() {}

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> findRegistrationsByPackageTypeAndDateRange(PackageType packageType, LocalDate startDate, LocalDate endDate) {
        return classRegistrationRepository.findRegistrationsByPackageTypeAndDateRange(packageType, startDate, endDate);
    }

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> getRegistrationsAfter(LocalDate date){
        return classRegistrationRepository.findAll(edu.miu.cs.cs544.lotu.springboot.project.specification.ClassRegistration.hasRegistrationDateAfter(date));
    }

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> getFilteredRegistrations(LocalDate date, int minCapacity, int minExperience) {
        // Combine multiple Specifications using `and`
        return classRegistrationRepository.findAll(
                edu.miu.cs.cs544.lotu.springboot.project.specification.ClassRegistration.hasRegistrationDateAfter(date)
                        .and(edu.miu.cs.cs544.lotu.springboot.project.specification.ClassRegistration.hasGymClassWithCapacityGreaterThan(minCapacity))
                        .and(edu.miu.cs.cs544.lotu.springboot.project.specification.ClassRegistration.hasGymClassWithTrainerHavingExperienceMoreThan(minExperience))
        );
    }
}
