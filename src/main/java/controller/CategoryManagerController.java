package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CategoryService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Valko Serhii on 9/21/2016.
 */
@Controller
@RequestMapping(value = "/config/categoryManager")
public class CategoryManagerController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String renderCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/config/categoryManager";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"data"})
    public void addCategory(HttpServletRequest httpServletRequest){
        String data = httpServletRequest.getParameter("data");
        categoryService.add(data);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"id"})
    public void removeCategory(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        categoryService.remove(id);
    }
}
