package edu.miu.cs.cs544.lotu.springboot.project.security;

import edu.miu.cs.cs544.lotu.springboot.project.entity.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private Credential credentialModel;

    public UserPrincipal(Credential credentialModel) {
        this.credentialModel = credentialModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.credentialModel.getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.credentialModel.getPassword();
    }

    @Override
    public String getUsername() {
        return this.credentialModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
