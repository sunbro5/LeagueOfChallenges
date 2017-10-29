package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Friend {
    private int friendId;
    private Timestamp created;
    private int userFriendId;
    private int userId;

    @Id
    @Column(name = "friend_id")
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "user_friend_id")
    public int getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(int userFriendId) {
        this.userFriendId = userFriendId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (friendId != friend.friendId) return false;
        if (userFriendId != friend.userFriendId) return false;
        if (userId != friend.userId) return false;
        if (created != null ? !created.equals(friend.created) : friend.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + userFriendId;
        result = 31 * result + userId;
        return result;
    }
}
