package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.session.UserSessionManager;
import com.astora.web.model.UserSecuredModel;
import com.astora.web.utils.CustomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseUserPage {

    private static final String USER_INFO_MESSAGE = "userInfoMessages";

    @Autowired
    protected UserSessionManager userSessionManager;

    /**
     *  Initialize model for menu and user basic info
     * @param model
     * @return
     */
    public Map<String, Object> init(Map<String, Object> model) {
        UserSecuredModel userSecuredModel = userSessionManager.getUserSecuredModelFromSession();
        if(userSecuredModel != null){
            model.put("userName", userSecuredModel.getUsername());
        }
        List<String> userInfos = userSessionManager.getUserInfo();
        if(!CustomValidationUtils.isEmpty(userInfos)){
            model.put(USER_INFO_MESSAGE,userInfos);
        }
        return model;
    }

    @SuppressWarnings("unchecked")
    public void pushInfo(Map<String, Object> model, String info){
        List<String> userInfos = (List<String>) model.get(USER_INFO_MESSAGE);
        if(userInfos == null){
            userInfos = new ArrayList<>();
        }
        userInfos.add(info);
        model.put(USER_INFO_MESSAGE, userInfos);
    }

    public Map<String, Object> init() {
        return init(new HashMap<String, Object>());
    }

    public int getUserId() throws ServiceException {
        return userSessionManager.getUserId();
    }

}
