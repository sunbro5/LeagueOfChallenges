package com.astora.web.controller;

import com.astora.web.model.UserSecuredModel;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

public class BaseUserController {
    public Map<String, Object> init(Authentication authentication){
        Map<String, Object> model = new HashMap<String, Object>();
        if(authentication != null && authentication.isAuthenticated()){
            // sensitive data from UserSecuredModel
            UserSecuredModel userSecuredModel = (UserSecuredModel) authentication.getPrincipal();
            model.put("userName",userSecuredModel.getUsername());
        }
        return model;
    }
}
