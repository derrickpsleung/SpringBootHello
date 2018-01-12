package hello;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.model.BaseUser;
import com.hello.model.Role;
import com.hello.repository.BaseUserRepository;

@RestController
@Configuration
public class HelloRestController {

	private static Log logger = LogFactory.getLog(HelloRestController.class);

	@Autowired
	private BaseUserRepository repository;

    @RequestMapping("/db")
    public String toDb() {
    	
    	logger.info("prepare user data: ");
    	
    	repository.deleteAll();

		// save a couple of customers
		repository.save(new BaseUser("Alice", "abc123", Arrays.asList(new String[] {Role.ADMIN.toString()})));
		repository.save(new BaseUser("Alla", "abc123", Arrays.asList(new String[] {Role.NORMAL_USER.toString()})));
		repository.save(new BaseUser("Bob", "bbc123", Arrays.asList(new String[] {Role.NORMAL_USER.toString()})));
		
		logger.info("BaseUser found with findAll():");
		logger.info("-------------------------------");
		for (BaseUser customer : repository.findAll()) {
			logger.info(customer);
		}
		
        return "insert completed";
    }

}