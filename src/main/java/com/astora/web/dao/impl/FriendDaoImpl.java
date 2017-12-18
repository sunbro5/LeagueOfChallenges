package com.astora.web.dao.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.model.Friend;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
@Repository("friendDao")
public class FriendDaoImpl extends EntityDaoImpl<Friend> implements FriendDao {

    protected FriendDaoImpl() {
        super(Friend.class);
    }
}
