package com.astora.web.model.validator;

import com.astora.web.enums.ReportReason;
import com.astora.web.model.UserReportModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Component("userReportModelValidator")
public class UserReportModelValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return UserReportModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nickname","report.form.error.nickname.IsEmpty");
    }
}
