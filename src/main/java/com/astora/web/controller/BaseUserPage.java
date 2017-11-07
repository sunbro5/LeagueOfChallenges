package com.astora.web.controller;

import com.astora.web.session.UserSessionManager;
import com.astora.web.model.UserSecuredModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class BaseUserPage {

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
        return model;
    }

    public Map<String, Object> init() {
        return init(new HashMap<String, Object>());
    }

    public int getUserId(){
        return userSessionManager.getUserId();
    }

}
