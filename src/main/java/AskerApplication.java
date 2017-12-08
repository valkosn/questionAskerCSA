import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("")
public class AskerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AskerApplication.class, args);
    }
}
