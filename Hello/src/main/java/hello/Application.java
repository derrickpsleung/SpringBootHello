package hello;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan({"hello", "com.hello"})
@EnableMongoRepositories("com.hello")
@SpringBootApplication
@EnableWebMvc
public class Application implements CommandLineRunner {
	
	private static Log logger = LogFactory.getLog(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
	public void run(String... args) throws Exception {

	}
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

        	logger.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            int i=0;
            for (String beanName : beanNames) {
            	System.out.println(i+". "+beanName);
            	i++;
            }

        };
    }

}