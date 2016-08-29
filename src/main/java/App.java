import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.QaService;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service.xml", "render.xml");
        QaService service = context.getBean("qa_service", QaService.class);
        Renderer renderer = context.getBean("console_renderer", Renderer.class);
        renderer.render(service.getAllQuestions());
    }
}
