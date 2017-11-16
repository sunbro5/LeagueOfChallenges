package com.astora.web.model.validator;

import com.astora.web.model.SendMessageModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 9.11.2017
 */
@Component("sendMessageModelValidator")
public class SendMessageModelValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return SendMessageModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"text","messages.form.error.text.isEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"toNickname","messages.form.error.toNickname.isEmpty");
    }
}
