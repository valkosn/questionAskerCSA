package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;
import service.QaService;

/**
 * Created by Valko Serhii on 01-Sep-16.
 */
@Controller
public class StartController {

    private QaService qaService;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/start")
    public String index(Model model) {
        model.addAttribute("maxQaAmount", qaService.getQuestionAmount());
        model.addAttribute("data", "undefined");
        model.addAttribute("questionsAmount", "undefined");
        model.addAttribute("timePerQuestion", "undefined");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }
}
