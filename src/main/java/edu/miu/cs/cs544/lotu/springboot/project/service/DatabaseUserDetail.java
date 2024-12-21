package edu.miu.cs.cs544.lotu.springboot.project.service;

import edu.miu.cs.cs544.lotu.springboot.project.repository.Credential;
import edu.miu.cs.cs544.lotu.springboot.project.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetail implements UserDetailsService {
    @Autowired
    private Credential credentialRepository;

    public DatabaseUserDetail() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credentialModel =
                credentialRepository.findByUsername(username)
                        .orElseThrow(() -> {
                            System.err.println("User not found: " + username);
                            return new UsernameNotFoundException("User not found with username: " + username);
                        });

        return new UserPrincipal(credentialModel);
    }
}
