package edu.miu.cs.cs544.lotu.springboot.project.service;

import edu.miu.cs.cs544.lotu.springboot.project.repository.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetails implements UserDetailsService {
    @Autowired
    private Credential credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credentialModel = credentialRepository.findByUsername(username);
        if (credentialModel == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(credentialModel);
    }
}
