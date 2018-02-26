package com.astora.web.service.impl;

import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.User;
import com.astora.web.model.UserSecuredModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
@Service("loginService")
 public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByNickname(username);
        if(user == null){
            throw new UsernameNotFoundException("Use with username: " + username + " not found.");
        }
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userDao.update(user);
        UserSecuredModel userModel = new UserSecuredModel();
        userModel.setPassword(user.getPassword());
        userModel.setUsername(user.getNickname());
        userModel.setUserId(user.getUserId());
        List<String> authorities = new ArrayList<String>();
        authorities.add(user.getRoleByRoleRoleId().getName());
        userModel.setAuthorities(authorities);
        return userModel;
    }
}
