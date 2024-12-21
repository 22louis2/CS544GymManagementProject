package edu.miu.cs.cs544.lotu.springboot.project.controller;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController(value = "classRegistration")
@RequestMapping(value = "/api/class-registrations")
public class ClassRegistration {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.service.ClassRegistration classRegistrationService;

    @GetMapping
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration>> findRegistrations(@RequestParam PackageType packageType,
                @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration> registrations = classRegistrationService.findRegistrationsByPackageTypeAndDateRange(packageType, startDate, endDate);
        return ResponseEntity.ok(registrations);
    }
}
