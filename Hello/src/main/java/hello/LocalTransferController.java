package hello;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hello.annotation.Layout;
import com.hello.annotation.ParentViewPath;
import com.hello.form.LocalTransferForm;
import com.hello.model.BaseUserDetails;
import com.hello.model.LocalTransfer;
import com.hello.repository.LocalTransferRepository;

@Controller
@Configuration
@Layout(value = "default-script")
@ParentViewPath(value = "fragments/transfer/local/")
public class LocalTransferController {

	private static Log logger = LogFactory.getLog(LocalTransferController.class);
	
	@Autowired
	private LocalTransferRepository localTransferRepository;
	
	@RequestMapping(method = RequestMethod.POST, value={"/processCreateLocalTransfer"})
    public String processCreateTransfer(Authentication authentication, @ModelAttribute("form") LocalTransferForm form) {
		
		BaseUserDetails userDetails = (BaseUserDetails) authentication.getPrincipal();
		logger.info(String.format("createLocalTransfer username[%s], id[%s]", userDetails.getUsername(), userDetails.getId()));
		
		for(LocalTransfer lt: form.getTransferInfoList()) {
			LocalTransfer tmp = new LocalTransfer();
			tmp.setBankName(lt.getBankName());
			tmp.setFromAccNo(lt.getFromAccNo());
			tmp.setToAccNo(lt.getToAccNo());
			tmp.setLogonUsername(lt.getLogonUsername());
			localTransferRepository.save(tmp);
		}
		
        return "viewLocalTransfer";
    }
	
	@RequestMapping(method = RequestMethod.GET, value={"/createLocalTransfer"})
    public String createLocalTransfer() {
        return "createLocalTransfer";
    }
	
	@RequestMapping(method = RequestMethod.GET, value={"/viewLocalTransfer"})
    public String viewLocalTransfer(Authentication authentication) {
		
		List<LocalTransfer> list = localTransferRepository.findByLogonUsername(authentication.getName());
		ModelAndView mv = new ModelAndView();
		mv.addObject("resultList", list);
        return "viewLocalTransfer";
    }

}