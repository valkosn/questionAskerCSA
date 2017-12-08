package com.asker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.asker.entity.QA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.asker.service.CategoryService;
import com.asker.service.QaService;

import java.io.IOException;

/**
 * Created by Valko Serhii on 9/21/2016.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/com/asker/config/questionManager**")
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

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String save(@RequestBody String question) throws IOException {
        QA qa = new ObjectMapper().readValue(question, QA.class);
        qaService.save(qa);
        return "";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        qaService.deleteQuestion(id);
        return "";
    }
}
