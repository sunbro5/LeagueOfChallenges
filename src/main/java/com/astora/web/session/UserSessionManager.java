package com.astora.web.session;

import com.astora.web.model.UserSecuredModel;

import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 7.11.2017
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

    int getUserId();
}
