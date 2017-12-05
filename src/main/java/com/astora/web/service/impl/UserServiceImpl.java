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
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<String> getUsersNicknameLike(String nickname) {
        List<User> users = userDao.getUserLikeNickname(nickname);
        List<String> nicknameList = new ArrayList<String>();
        for (User user : users) {
            nicknameList.add(user.getNickname());
        }
        return nicknameList;
    }

    public User getUserById(int userId) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
        }
        return user;
    }

    public User getUserByNickname(String nickname) throws UserDoesntExists {
        User user = userDao.getUserByNickname(nickname);
        if (user == null) {
            throw new UserDoesntExists("User with nickname" + nickname + "does not exists");
        }
        return user;
    }

    public UserInfoDto getUserInfoByNickname(String nickname) throws UserDoesntExists {
        User user = getUserByNickname(nickname);
        return null;
    }

    private UserInfoDto mapUserInfo(User user){
        UserInfoDto userInfo = new UserInfoDto(user);
        Map<ReportReason, Integer> reportList = new HashMap<ReportReason, Integer>();
        for (Report report: user.getReportsByUserId()){
            ReportReason reason = ReportReason.valueOf(report.getReason());
            Integer count = reportList.get(reason);
            reportList.put(reason,count + 1);
        }
        userInfo.setReportList(reportList);
        return userInfo;
    }

}
