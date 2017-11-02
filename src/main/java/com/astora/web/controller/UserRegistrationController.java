package com.astora.web.controller;

import com.astora.web.model.RegistrationModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 1.11.2017
 */
@Controller()
public class UserRegistrationController {

    @RequestMapping("/registration")
    public ModelAndView renderRegisterPage(Map<String, Object> model){

        return new ModelAndView("registration",model);
    }

    @RequestMapping("/createUser")
    public ModelAndView registerUser(@Valid @ModelAttribute RegistrationModel registerModel, Map<String, Object> model, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("registration",model);
        }
        return new ModelAndView("redirect:login",model);
    }


    @ModelAttribute(RegistrationModel.MODEL_NAME)
    RegistrationModel getRegisterModel(){
        return new RegistrationModel();
    }
}
