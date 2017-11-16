package com.astora.web.session;

import com.astora.web.model.UserSecuredModel;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 7.11.2017
 */
public interface UserSessionManager {

    /**
     *  method return UserSecuredModel from session with sensitive data !!!
     *  be careful where you put them !!!
     * @return UserSecuredModel
     */
    UserSecuredModel getUserSecuredModelFromSession();

    void putUserInfo(String info);

    List<String> getUserInfo();

    /**
     * method return user id by session, if user is not authorized return 0
     *
     * @param
     * @return
     */
    int getUserId();
}
