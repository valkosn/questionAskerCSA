package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Valko Serhii on 01-Sep-16.
 */
@Controller
public class StartController {
    @RequestMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
