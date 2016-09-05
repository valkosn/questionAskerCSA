package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.QaService;
import utils.JsonFileConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Valko Serhii on 02-Sep-16.
 */

@Controller
public class TestController {
    private QaService qaService;

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @RequestMapping("/test")
    public String test(Model model, HttpServletRequest httpServletRequest) {
        String testType = httpServletRequest.getParameter("testType");
        int questionsAmount = Integer.parseInt(httpServletRequest.getParameter("questionsAmount"));
        int timePerQuestion = Integer.parseInt(httpServletRequest.getParameter("timePerQuestion"));
        model.addAttribute("questionsAmount", questionsAmount);
        model.addAttribute("timePerQuestion" , timePerQuestion);
        switch (testType) {
            case "normal" : {
                model.addAttribute("additionalJS", "remoteL");
                break;
            }
            case "offline" : {
                model.addAttribute("additionalJS", "localL");
                break;
            }
        }
        model.addAttribute("scriptToRun" , "startTest()");
        try {
            model.addAttribute("data", JsonFileConverter.toJSONString(qaService.getRandomQuestions(questionsAmount)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
