package com.astora.web.dao;

import com.astora.web.dao.model.Avatar;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 04.11.2017.
 */
public interface AvatarDao extends EntityDao<Avatar> {
    Avatar getByAvatarName(String name);
}
