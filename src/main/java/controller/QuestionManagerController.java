package controller;

import enteties.QA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.QaService;

import java.util.List;

/**
 * Created by Valko Serhii on 9/21/2016.
 */
@Controller
@RequestMapping(value = "/config/questionManager")
public class QuestionManagerController {

    private QaService qaService;

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String render (Model model){
        List<QA> allQuestions = qaService.getAllQuestions();
        model.addAttribute("questions", allQuestions);
        return "/config/questionManager";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"id", "type"})
    public String action(@RequestParam(value = "id") Integer id,
                       @RequestParam(value = "type") String type){
        switch (type){
            case "edit": {

                break;
            }
            case "rm": {
                qaService.delete(id);
                return "/config/questionManager";
            }
        }
        return "/config/questionManager";
    }
}
