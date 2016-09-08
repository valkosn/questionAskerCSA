package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Valko Serhii on 08-Sep-16.
 */
@Controller
public class ConfigController {

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String getConfig(){
        return "config";
    }
}
