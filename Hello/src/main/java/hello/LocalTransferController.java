package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hello.annotation.Layout;
import com.hello.annotation.ParentViewPath;
import com.hello.config.SystemConfig;
import com.hello.model.BaseUserDetails;
import com.hello.repository.BaseUserRepository;
import com.hello.repository.LocalTransferRepository;

@Controller
@Configuration
@Layout(value = SystemConfig.DEFAULT_LAYOUT_PATH)
@ParentViewPath(value = "fragments/transfer/local/")
public class LocalTransferController {

	private static Log logger = LogFactory.getLog(LocalTransferController.class);
	
	@Autowired
	private BaseUserRepository usrRepository;
	
	@Autowired
	private LocalTransferRepository localTransferRepository;
	
	/* ***** Example *****
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    */
	
	@RequestMapping(method = RequestMethod.POST, value={"/processCreateLocalTransfer"})
    public String processCreateTransfer(Authentication authentication) {
		BaseUserDetails userDetails = (BaseUserDetails) authentication.getPrincipal();
        return "viewLocalTransfer";
    }
	
	@RequestMapping(method = RequestMethod.GET, value={"/createLocalTransfer"})
    public String createLocalTransfer(Authentication authentication) {
		BaseUserDetails userDetails = (BaseUserDetails) authentication.getPrincipal();
		logger.info(String.format("createLocalTransfer username[%s], id[%s]", userDetails.getUsername(), userDetails.getId()));
        return "createLocalTransfer";
    }
	
	@RequestMapping(method = RequestMethod.GET, value={"/viewLocalTransfer"})
    public String viewLocalTransfer() {
        return "viewLocalTransfer";
    }

}