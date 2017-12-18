package com.astora.web.utils;

import com.astora.web.dao.model.Game;
import com.astora.web.dao.model.User;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 13.12.2017
 */
public class TeamUtils {

    public static String getDefaultTeamName(User user, Game game){
        StringBuilder text = new StringBuilder();
        text.append("default");
        text.append(user.getNickname());
        text.append("-");
        text.append(game.getGameName());
        return text.toString();
    }
}
