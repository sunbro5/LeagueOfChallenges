package com.astora.web.service.impl;

import com.astora.web.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
@Service("loginService")
 public class LoginServiceImpl implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = new  UserModel();
        user.setPassword("123");
        user.setUsername("admin");
        return user;
    }
}
