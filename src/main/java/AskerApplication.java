import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"com.asker"})
@ImportResource({
        "classpath:applicationContext.xml",
        "classpath:mvc-config.xml"})
public class AskerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AskerApplication.class, args);
    }
}
