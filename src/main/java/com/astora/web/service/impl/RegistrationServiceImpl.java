package com.astora.web.service.impl;

import com.astora.web.dao.AvatarDao;
import com.astora.web.dao.RoleDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Avatar;
import com.astora.web.dao.model.Role;
import com.astora.web.dao.model.User;
import com.astora.web.enums.RoleType;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserAlreadyExistsException;
import com.astora.web.mapper.UserMapper;
import com.astora.web.model.RegistrationModel;
import com.astora.web.service.RegistrationService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 1.11.2017
 */
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private static final String DEFAULT_AVATAR_NAME = "avatar_1";
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AvatarDao avatarDao;

    @Transactional
    public void createUser(RegistrationModel registration) throws ServiceException {
        User alreadyCreated = userDao.getUserByNickname(registration.getNickname());
        if (alreadyCreated != null) {
            throw new UserAlreadyExistsException("User name: " + registration.getNickname() + "already exists.");
        }
        User user = userMapper.registrationModelToUser(registration);
        user.setPassword(new BCryptPasswordEncoder().encode(registration.getPassword()));

        Role role = roleDao.getRoleByRoleType(RoleType.USER);
        if(role == null){
            throw new ServiceException("Cant load role.");
        }
        user.setRoleByRoleRoleId(role);
        Avatar avatar = avatarDao.getByAvatarName(DEFAULT_AVATAR_NAME);
        if(avatar == null){
            throw new ServiceException("Cant load avatar.");
        }
        user.setAvatarByAvatarsAvatarId(avatar);
        userDao.create(user);
    }

}
