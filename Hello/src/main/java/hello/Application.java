package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hello.interceptor.ThymeleafLayoutInterceptor;

@ComponentScan({"hello", "com.hello"})
@EnableMongoRepositories("com.hello")
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner{
	
	private static Log logger = LogFactory.getLog(Application.class);
	
    @Bean(value="localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    //!!! remember to define the value (or the method name = messageSource) in order to override the default setting!!!
    @Bean(value="messageSource")
    public MessageSource getMessageResource() throws IOException {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
         
        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        PathMatchingResourcePatternResolver pmrps = new PathMatchingResourcePatternResolver();
        Resource[] resources = pmrps.getResources("classpath:i18n/**/*.properties");
        ArrayList<String> propertiesPathList = new ArrayList<String>();
        for(Resource src: resources) {
            if(src.getFilename().indexOf("_") < 0) {
                String url = src.getURL().toString();
                url = url.substring(0, url.lastIndexOf("."));
                logger.info(String.format("messageSource: %s", url));
                propertiesPathList.add(url);
            }
        }
        String[] propertiesPathArr = new String[propertiesPathList.size()];
        propertiesPathArr = propertiesPathList.toArray(propertiesPathArr);

        messageResource.setBasenames(propertiesPathArr);
        messageResource.setDefaultEncoding("UTF-8");
        
        return messageResource;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //for accessing webjars in front-end
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/i18n/**").addResourceLocations("classpath:/i18n/");
    }

	
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