import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class App {

    public static void main(String[] args) {
        // FIXME: 10/19/2016 set correct application-context path...
        System.out.println("//|\\");
        ApplicationContext context = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/config/application-context.xml");
        Renderer renderer = context.getBean("console_renderer", Renderer.class);
        renderer.render();

    }
}
