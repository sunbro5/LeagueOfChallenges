package com.astora.web.controller;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.CantSendMessageException;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;
import com.astora.web.model.validator.SendMessageModelValidator;
import com.astora.web.service.MessageService;
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
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 6.11.2017
 */
@Controller
public class MessageController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

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
        return renderMessages(messageModel, init());
    }

    private ModelAndView renderMessages(SendMessageModel messageModel, Map<String, Object> model) {
        List<UserMessagesDto> messagesPreview = null;
        List<MessageDto> messages = null;
        try {
            messagesPreview = messageService.getNewestMessagesPreview(getUserId());
        } catch (ServiceException e) {
            logger.error(e);
        }

        if(!CustomValidationUtils.isEmpty(messageModel.getToNickname())){
            try {
                messages = messageService.getUserMessagesWithUser(getUserId(),messageModel.getToNickname());
            } catch (ServiceException e) {
                logger.error(e);
            }
        }
        model.put(SendMessageModel.MODEL_NAME,messageModel);
        model.put("userMessages",messages);
        model.put("userMessagesPreview",messagesPreview);
        return new ModelAndView("messages", model);
    }

    //TODO should be REST
    @RequestMapping("/sendMessage")
    public ModelAndView sendMessage(@ModelAttribute(SendMessageModel.MODEL_NAME) @Validated SendMessageModel messageModel, BindingResult result){
        Map<String, Object> map = init();
        if(result.hasErrors()){
            return renderMessages(messageModel,map);
        }
        try {
            messageService.sendMessage(getUserId(),messageModel);
        } catch (CantSendMessageException e) {
            userSessionManager.putUserInfo("message.messageSent.timeLimit");
            return renderMessages(messageModel,map);
        } catch (UserDoesntExists e) {
            result.rejectValue("toNickname","messages.form.error.userNickname.userDoesntExists");
            return renderMessages(messageModel,map);
        } catch (ServiceException e) {
            logger.info(e);
            return renderMessages(messageModel,map);
        }
        userSessionManager.putUserInfo("message.messageSent.successful");
        return renderMessages(messageModel,map);
    }

    @ModelAttribute(SendMessageModel.MODEL_NAME)
    public SendMessageModel getSendMessageModel(){
        return new SendMessageModel();
    }
}
