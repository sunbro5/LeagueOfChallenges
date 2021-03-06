package com.astora.web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Be aware !!!! this class is used for spring security or getting sensitive values
 *
 *
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
public class UserSecuredModel implements UserDetails {

    private int userId;
    private String username;
    private String password;
    private List<String> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
        for(String authority: authorities){
            auth.add(new SimpleGrantedAuthority(authority));
        }
        return auth;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName(){
        return username;
    }
}
