package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserAlreadyExistsException;
import com.astora.web.model.RegistrationModel;
import com.astora.web.model.validator.RegisterModelValidator;
import com.astora.web.service.RegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 1.11.2017
 */
@Controller()
public class RegistrationController extends BaseUserController {

    private final Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private RegisterModelValidator registerModelValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(registerModelValidator);
    }

    @Autowired
    private RegistrationService registrationService;


    @RequestMapping("/registration")
    public ModelAndView showRegistration(Authentication authentication) {
        return renderRegisterPage(authentication);
    }

    private ModelAndView renderRegisterPage(Authentication authentication) {
        Map<String, Object> model = init(authentication);
        return new ModelAndView("registration", model);
    }


    @RequestMapping("/createUser")
    public ModelAndView registerUser(@ModelAttribute("registrationModel") @Validated RegistrationModel registerModel, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return renderRegisterPage(authentication);
        }
        try {
            registrationService.createUser(registerModel);
        } catch (UserAlreadyExistsException e) {
            result.rejectValue("nickname", "registration.form.error.user.alreadyExists");
            return renderRegisterPage(authentication);
        } catch (ServiceException e) {
            logger.error("Service exception while creating new user " + registerModel, e);
            return renderRegisterPage(authentication);
        }
        return new ModelAndView("redirect:login");
    }


    @ModelAttribute(RegistrationModel.MODEL_NAME)
    RegistrationModel getRegisterModel() {
        return new RegistrationModel();
    }
}
