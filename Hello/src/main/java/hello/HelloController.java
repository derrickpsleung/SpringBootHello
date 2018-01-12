package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hello.annotation.Layout;
import com.hello.config.SystemConfig;

@Controller
@Configuration
@Layout(value = SystemConfig.DEFAULT_LAYOUT_PATH)
public class HelloController implements ErrorController{

	private static Log logger = LogFactory.getLog(HelloController.class);
	
	private static final String ERR_PATH = "/error";

	
	@ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
	
	/* ***** Example *****
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    */
	
	@RequestMapping(value={"/login"})
    public String login() {
        return "fragments/basic/login";
    }
	
	@RequestMapping(value= {"/","/home"})
    public String home() {
        return "fragments/basic/home";
    }
	
    @RequestMapping(value=ERR_PATH)
    public String error() {
        return "error/err";
    }
	
	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}

}