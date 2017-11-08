package com.astora.web.session.impl;

import com.astora.web.session.UserSessionManager;
import com.astora.web.model.UserSecuredModel;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 7.11.2017
 */
@Component("userSessionManager")
public class UserSessionManagerImpl implements UserSessionManager {

    private static final String USER_INFO_MESSAGE = "user_info_message";

    private final Logger logger = Logger.getLogger(UserSessionManagerImpl.class);

    public UserSecuredModel getUserSecuredModelFromSession() {
        UserSecuredModel userSecuredModel = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userSecuredModel = (UserSecuredModel) authentication.getPrincipal();
        }
        return userSecuredModel;
    }

    /**
     * method return user id by session, if user is not authorized return 0
     *
     * @param
     * @return
     */
    public int getUserId() {
        UserSecuredModel userSecuredModel = getUserSecuredModelFromSession();
        if (userSecuredModel != null) {
            return userSecuredModel.getUserId();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public void putUserInfo(String info){
        List<String> list = null;
        Object object = getSessionAttribute(USER_INFO_MESSAGE);
        if(object == null || !(object instanceof ArrayList)){
            list = new ArrayList<String>();
            list.add(info);
            setSessionAttribute(USER_INFO_MESSAGE, list);
        } else {
            // wtf iam doing ???? its 3:AM i should sleep
            list = (List<String>) getSessionAttribute(USER_INFO_MESSAGE);
            list.add(info);
            setSessionAttribute(USER_INFO_MESSAGE,list);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getUserInfo(){
        List<String> list = (List<String>) getSessionAttribute(USER_INFO_MESSAGE);
        setSessionAttribute(USER_INFO_MESSAGE,new ArrayList<String>());
        return list;
    }


    public void setSessionAttribute(String name, Object object) {
        HttpSession session = getHttpSession();
        if (session == null) {
            logger.info("Session is null cant save session attribute name: " + name);
        } else {
            session.setAttribute(name,object);
        }
    }

    public Object getSessionAttribute(String name) {
        HttpSession session = getHttpSession();
        if (session == null) {
            logger.info("Session is null cant get session attribute name: " + name);
            return null;
        } else {
            return session.getAttribute(name);
        }
    }


    private HttpSession getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request == null || request.getSession() == null) {
            logger.info("HttpServletRequest or HttpSession is null");
            return null;
        }
        return request.getSession();
    }

}