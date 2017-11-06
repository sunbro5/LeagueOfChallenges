package com.astora.web.service;

import com.astora.web.dto.FriendInfo;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
public interface UserService {

    List<FriendInfo> getFriendList(int userId);
}
