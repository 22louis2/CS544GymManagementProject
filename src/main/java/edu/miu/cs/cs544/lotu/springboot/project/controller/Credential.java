package edu.miu.cs.cs544.lotu.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("credentialController")
@RequestMapping("/api/credentials")
public class Credential {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.service.Credential credentialService;

    public Credential() {}

    @PostMapping("/register")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential>
    register(@RequestBody edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credential) {
        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential savedCredential =
                credentialService.register(credential);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCredential);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        boolean response = credentialService.login(username, password);
        return response ? ResponseEntity.ok("Login successful") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials provided");
    }

    @GetMapping("/{username}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsername(@PathVariable String username) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> credential = credentialService.findByUsername(username);
        return credential.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/locked/{username}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsernameLocked(@PathVariable String username) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> lockedCredential = credentialService.findByUsernameLockedForUpdate(username);
        return lockedCredential.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
