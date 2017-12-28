package com.astora.web.model.validator;

import com.astora.web.model.NewTeamModel;
import com.astora.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 21.12.2017
 */
@Component("newTeamModelValidator")
public class NewTeamModelValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return NewTeamModel.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        NewTeamModel model = (NewTeamModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","newTeam.form.error.name.isEmpty");
    }
}
