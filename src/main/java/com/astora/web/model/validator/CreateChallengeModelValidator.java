package com.astora.web.model.validator;

import com.astora.web.model.CreateChallengeModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 28.12.2017
 */
@Component("createChallengeModelValidator")
public class CreateChallengeModelValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return CreateChallengeModel.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        CreateChallengeModel createChallengeModel = (CreateChallengeModel) target;

    }
}
