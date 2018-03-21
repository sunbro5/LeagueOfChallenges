package com.astora.web.service.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.MessageDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Friend;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfoDto;
import com.astora.web.dto.UserInfoDto;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.enums.ReportReason;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;
import com.astora.web.service.PropertyService;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public void updateUser(User user) {
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    public List<String> getUsersNicknameLike(String nickname) {
        List<User> users = userDao.getUserLikeNickname(nickname);
        return users.stream().map(User::getNickname).collect(Collectors.toList());
    }

    @Transactional
    public User getUserById(int userId) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
        }
        return user;
    }

    @Transactional
    public User getUserByNickname(String nickname) throws UserDoesntExists {
        User user = userDao.getUserByNickname(nickname);
        if (user == null) {
            throw new UserDoesntExists("User with nickname" + nickname + "does not exists");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfoByNickname(String nickname) throws UserDoesntExists {
        User user = getUserByNickname(nickname);
        UserInfoDto userInfo = mapUserInfo(user);
        return userInfo;
    }

    public UserInfoDto mapUserInfo(User user) {
        UserInfoDto userInfo = new UserInfoDto(user);
        Map<ReportReason, Integer> reportList = new HashMap<ReportReason, Integer>();
        for (Report report : user.getReportsByUserId()) {
            ReportReason reason;
            try {
                reason = ReportReason.valueOf(report.getReason());
            } catch (IllegalArgumentException e) {
                logger.error(e);
                continue;
            }
            Integer count = reportList.get(reason);
            if (count != null) {
                count = count + 1;
            } else {
                count = 1;
            }
            reportList.put(reason, count);
        }
        userInfo.setReportList(reportList);
        return userInfo;
    }


}
