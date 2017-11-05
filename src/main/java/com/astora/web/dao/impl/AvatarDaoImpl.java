package com.astora.web.dao.impl;

import com.astora.web.dao.AvatarDao;
import com.astora.web.dao.model.Avatar;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 04.11.2017.
 */
@Repository("avatarDao")
@Transactional
public class AvatarDaoImpl extends EntityDaoImpl<Avatar> implements AvatarDao {

    protected AvatarDaoImpl() {
        super(Avatar.class);
    }

    public Avatar getByAvatarName(String name){
        return getByUniqueColumnValue("avatarName",name);
    }
}
