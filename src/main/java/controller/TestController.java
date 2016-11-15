package controller;

import enteties.QA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.QaService;
import utils.JsonFileConverter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test(Model model, HttpServletRequest httpServletRequest) {
        String testType = httpServletRequest.getParameter("testType");
        int questionsAmount = Integer.parseInt(httpServletRequest.getParameter("questionsAmount"));
        int timePerQuestion = Integer.parseInt(httpServletRequest.getParameter("timePerQuestion"));
        String[] categories = httpServletRequest.getParameterValues("categories");
        List<QA> data = qaService.getRandomQuestions(questionsAmount, categories);
        model.addAttribute("questionsAmount", questionsAmount);
        model.addAttribute("timePerQuestion", timePerQuestion);
        switch (testType) {
            case "normal": {
                model.addAttribute("additionalJS", "remoteL");
                break;
            }
            case "offline": {
                model.addAttribute("additionalJS", "localL");
                break;
            }
        }
        if (data.size() >= questionsAmount) {
            try {
                model.addAttribute("data", new JsonFileConverter().toJSONString(data));

            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("scriptToRun", "startTest()");
        } else {
            model.addAttribute("alert", "Not enough questions, check categories or question amount");
        }
        return "index";
    }
}
