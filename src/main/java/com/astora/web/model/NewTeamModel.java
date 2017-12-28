package com.astora.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 20.12.2017
 */
public class NewTeamModel {

    public static final String MODEL_NAME = "newTeamModel";

    private String name;
    private String description;
    private String gameName;
    private List<String> users;

    public NewTeamModel(){

    }

    public NewTeamModel(int usersCount){
        users = new ArrayList<String>(usersCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
