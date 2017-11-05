package com.astora.web.model.validator;

import com.astora.web.model.RegistrationModel;
import com.astora.web.utils.CustomValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017.
 */
public class RegisterModelValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return RegistrationModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        RegistrationModel model = (RegistrationModel) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "registration.form.error.label.isEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "registration.form.error.label.isEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "registration.form.error.label.isEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "registration.form.error.label.isEmpty");
        if (!(model.getPassword().length() > 4)) {
            errors.rejectValue("password", "registration.form.error.password.toShort");
        }
        if (!model.getPassword().equals(model.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "registration.form.error.passwords.notEquals");
        }
        Pattern pattern = Pattern.compile(CustomValidationUtils.EMAIL_PATTERN,
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(model.getEmail()).matches())) {
            errors.rejectValue("email", "registration.form.error.email.notValid");
        }
    }
}
