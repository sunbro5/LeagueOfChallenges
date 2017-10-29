package com.astora.web.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Friend {
    private int friendId;
    private Timestamp created;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (friendId != friend.friendId) return false;
        if (created != null ? !created.equals(friend.created) : friend.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
