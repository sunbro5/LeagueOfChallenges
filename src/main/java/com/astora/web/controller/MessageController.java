package com.astora.web.controller;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;
import com.astora.web.model.validator.SendMessageModelValidator;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 6.11.2017
 */
@Controller
public class MessageController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SendMessageModelValidator sendMessageModelValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(sendMessageModelValidator);
    }


    @RequestMapping("/messages")
    public ModelAndView showMessages(@RequestParam(value = "friendMessages",required = false) String friendMessages) {
        SendMessageModel messageModel = getSendMessageModel();
        messageModel.setToNickname(friendMessages);
        return renderMessages(messageModel, init(),friendMessages);
    }

    public ModelAndView renderMessages(){
        return renderMessages(getSendMessageModel(),init(),null);
    }

    public ModelAndView renderMessages(SendMessageModel messageModel, Map<String, Object> model){
        return renderMessages(messageModel,model,null);
    }

    public ModelAndView renderMessages(SendMessageModel messageModel, Map<String, Object> model, String friendNickname) {
        List<UserMessagesDto> messagesPreview = null;
        List<MessageDto> messages = null;
        try {
            messagesPreview = userService.getNewestMessagesPreview(getUserId());
        } catch (ServiceException e) {
            logger.error(e);
        }

        if(!CustomValidationUtils.isEmpty(messageModel.getToNickname())){
            try {
                messages = userService.getUserMessagesWithUser(getUserId(),friendNickname);
            } catch (ServiceException e) {
                logger.error(e);
            }
        }
        model.put(SendMessageModel.MODEL_NAME,messageModel);
        model.put("userMessages",messages);
        model.put("userMessagesPreview",messagesPreview);
        return new ModelAndView("messages", model);
    }

    @RequestMapping("/sendMessage")
    public ModelAndView sendMessage(@ModelAttribute(SendMessageModel.MODEL_NAME) @Validated SendMessageModel messageModel, BindingResult result){
        if(result.hasErrors()){
            return renderMessages();
        }
        try {
            userService.sendMessage(getUserId(),messageModel);
        } catch (UserDoesntExists e) {
            result.rejectValue("toNickname","messages.form.error.userNickname.userDoesntExists");
            return renderMessages();
        } catch (ServiceException e) {
            logger.info(e);
            return renderMessages();
        }
        userSessionManager.putUserInfo("message.messageSent.successful");
        return renderMessages(messageModel,init());
    }

    @ModelAttribute(SendMessageModel.MODEL_NAME)
    public SendMessageModel getSendMessageModel(){
        return new SendMessageModel();
    }
}
