package controller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import enteties.QA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.QaService;

/**
 * Created by Valko Serhii on 9/21/2016.
 */
@RestController
@RequestMapping(value = "/config/questionManager**")
public class QuestionManagerController {

    private QaService qaService;
    private CategoryService categoryService;

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Model render(Model model) {
        model.addAttribute("questions", qaService.getAllQuestions());
        model.addAttribute("categories", categoryService.getAllCategories());
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody QA get (@PathVariable(value = "id") Integer id) {
        return qaService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody QA question){
        question.getId();
        return "";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        qaService.deleteQuestion(id);
        return "";
    }
}
