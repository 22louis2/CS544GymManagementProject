package edu.miu.cs.cs544.lotu.springboot.project.service;

import edu.miu.cs.cs544.lotu.springboot.project.exception.UsernameAlreadyExistsException;
import edu.miu.cs.cs544.lotu.springboot.project.jms.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("credentialService")
public class Credential {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.repository.Credential credentialRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private Sender sender;

    public Credential() {}

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsername(String username) {
        return credentialRepository.findByUsername(username);
    }

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> findByUsernameLockedForUpdate(String username) {
        return credentialRepository.findByUsernameLockedForUpdate(username);
    }

    public edu.miu.cs.cs544.lotu.springboot.project.entity.Credential register(edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credentialModel) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> existingCredential = credentialRepository.findByUsername(credentialModel.getUsername());
        if (existingCredential.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        credentialModel.setPassword(bCryptPasswordEncoder.encode(credentialModel.getPassword()));
        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential savedCredential = credentialRepository.save(credentialModel);

        String message = String.format("User %s has been successfully registered.", savedCredential.getUsername());
        sender.send(message);

        return savedCredential;
    }

    public boolean login(String username, String password) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Credential> user = credentialRepository.findByUsername(username);
        boolean isAuthenticated = user.isPresent() && bCryptPasswordEncoder.matches(password, user.get().getPassword());

        if (isAuthenticated) {
            String message = String.format("User %s has successfully logged in.", username);
            sender.send(message);
        } else {
            String message = String.format("Failed login attempt for user %s.", username);
            sender.send(message);
        }
        return isAuthenticated;
    }
}
