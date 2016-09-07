package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.QaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Valko Serhii on 05-Sep-16.
 */
@Controller
public class EvaluateController {
    private QaService qaService;

    @Autowired
    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public HttpServletResponse evaluate (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        int questionID = Integer.parseInt(httpServletRequest.getParameter("data"));
        String correctAnswer = qaService.getCorrectAnswer(questionID);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(correctAnswer);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
        return httpServletResponse;


    }
}
