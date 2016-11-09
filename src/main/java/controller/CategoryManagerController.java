package controller;

import enteties.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Valko Serhii on 9/21/2016.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/config/categoryManager**")
public class CategoryManagerController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Model render(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, params = {"data"})
    public void addCategory(HttpServletRequest httpServletRequest){
        String data = httpServletRequest.getParameter("data");
        categoryService.add(new Category(data));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void removeCategory(@PathVariable(value = "id") Integer id){
        categoryService.remove(id);
    }
}
