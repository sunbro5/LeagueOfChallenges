package com.astora.web.mapper;

import com.astora.web.dao.model.User;
import com.astora.web.model.RegistrationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 13.2.2018
 */
@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User registrationModelToUser(RegistrationModel model);
}
