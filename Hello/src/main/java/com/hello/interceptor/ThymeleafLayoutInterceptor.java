package com.hello.interceptor;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hello.annotation.Layout;
import com.hello.annotation.ParentViewPath;
import com.hello.config.SystemConfig;

@Configuration
@PropertySource(value="classpath:/web.properties")
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {
	 

//	@Value("${mvc.default.layout}")
//    private String defaultLayout;
//    
//
//	@Value("${mvc.default.view.attribute.name}")
//    private String viewAttributeName;
 
	private static Log logger = LogFactory.getLog(ThymeleafLayoutInterceptor.class);
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	
    	logger.info("ThymeleafLayoutInterceptor.postHandle");
    	
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }
        String layoutName = getLayoutName(handler);
        //set template
        modelAndView.setViewName(layoutName);
        
        ParentViewPath pvp = getMethodOrTypeAnnotation((HandlerMethod)handler, ParentViewPath.class);
        //set fragment
        modelAndView.addObject(SystemConfig.DEFAULT_VIEW_ATTRIBUTE_NAME, (pvp==null?"":pvp.value())+originalViewName);

    }
 
    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }
 
    private String getLayoutName(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Layout layout = getMethodOrTypeAnnotation(handlerMethod, Layout.class);
            if (layout != null) {
                return layout.value();
            }
        }
        return SystemConfig.DEFAULT_LAYOUT_PATH;
    }
 
    private <A extends Annotation> A getMethodOrTypeAnnotation(HandlerMethod handlerMethod, Class<A> clazz) {
    	A annotation = handlerMethod.getMethodAnnotation(clazz);
        if (annotation == null) {
            return handlerMethod.getBeanType().getAnnotation(clazz);
        }
        return annotation;
    }
}
