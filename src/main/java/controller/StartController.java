package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.QaService;

/**
 * Created by Valko Serhii on 01-Sep-16.
 */
@Controller
public class StartController {

    private QaService qaService;

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @RequestMapping("/start")
    public String index(Model model) {
        model.addAttribute("maxQaAmount", qaService.getQuestionAmount());
        model.addAttribute("data", "undefined");
        model.addAttribute("questionsAmount", "undefined");
        model.addAttribute("timePerQuestion", "undefined");
        return "index";
    }
}
