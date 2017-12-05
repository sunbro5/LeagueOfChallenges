package com.astora.web.controller;

import com.astora.web.enums.ReportReason;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.UserReportModel;
import com.astora.web.model.validator.UserReportModelValidator;
import com.astora.web.service.ReportService;
import com.astora.web.session.UserSessionManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Controller
public class ReportController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(ReportController.class);

    @Autowired
    private UserReportModelValidator userReportModelValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userReportModelValidator);
    }


    @Autowired
    private ReportService reportService;


    @RequestMapping("/report")
    public ModelAndView showReport(){
        return renderReport();
    }

    private ModelAndView renderReport(){
        return renderReport(init());
    }


    private ModelAndView renderReport(Map<String, Object> model){
        model.put("reportReasonTypes", ReportReason.values());
        return new ModelAndView("report",model);
    }

    @RequestMapping("/sendReport")
    public ModelAndView sendReport(@Validated @ModelAttribute(UserReportModel.MODEL_NAME) UserReportModel reportModel, BindingResult result){
        if(result.hasErrors()){
            renderReport();
        }
        try {
            reportService.createReport(getUserId(),reportModel);
        } catch (UserDoesntExists e) {
            userSessionManager.putUserInfo("message.report.userDoesntExists");
            return renderReport();
        } catch (ServiceException e) {
            logger.error(e);
        }
        userSessionManager.putUserInfo("message.report.successfullyCreated");
        return renderReport();
    }

    @ModelAttribute(UserReportModel.MODEL_NAME)
    public UserReportModel getUserReportModel(){
        return new UserReportModel();
    }

}
