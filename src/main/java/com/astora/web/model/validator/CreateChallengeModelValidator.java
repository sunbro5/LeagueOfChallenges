package com.astora.web.model.validator;

import com.astora.web.exception.ServiceException;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.utils.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.util.Date;

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
        Timestamp start = null;
        Timestamp end = null;
        try {
            start = DateUtil.convertToTimestamp(createChallengeModel.getChallengeStart());
        } catch (ServiceException e) {
            errors.rejectValue("challengeStart","createChallenge.form.error.date.wrongFormat");
        }
        try {
            end = DateUtil.convertToTimestamp(createChallengeModel.getChallengeEnd());
        } catch (ServiceException e) {
            errors.rejectValue("challengeEnd","createChallenge.form.error.date.wrongFormat");
        }
        if(start != null && start.before(new Timestamp(new Date().getTime()))){
            errors.rejectValue("challengeEnd","createChallenge.form.error.date.startBeforeSysdate");
        }
        if(start != null && end != null  && end.before(start)){
            errors.rejectValue("challengeEnd","createChallenge.form.error.date.endBeforeStart");
        }
    }
}
