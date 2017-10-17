package com.astora.web.service;

import com.astora.web.dto.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
 public class LoginService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = new  UserModel();
        user.setPassword("123");
        user.setUsername("admin");
        return user;
    }
}
