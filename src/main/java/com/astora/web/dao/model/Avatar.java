package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Avatar {
    private int avatarId;
    private Timestamp created;
    private byte[] avatarImage;
    private String avatarName;

    @Id
    @Column(name = "avatar_id")
    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
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
    @Column(name = "avatar_image")
    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }

    @Basic
    @Column(name = "avatar_name")
    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avatar avatar = (Avatar) o;

        if (avatarId != avatar.avatarId) return false;
        if (created != null ? !created.equals(avatar.created) : avatar.created != null) return false;
        if (!Arrays.equals(avatarImage, avatar.avatarImage)) return false;
        if (avatarName != null ? !avatarName.equals(avatar.avatarName) : avatar.avatarName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = avatarId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(avatarImage);
        result = 31 * result + (avatarName != null ? avatarName.hashCode() : 0);
        return result;
    }
}
