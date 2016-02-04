package cl.doman.springmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SecurityInterceptor extends HandlerInterceptorAdapter  {
	private static final transient Logger log =LoggerFactory.getLogger(SecurityInterceptor.class);

	
	
	@Override
	public void postHandle(
			HttpServletRequest request,HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) 
					throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> pathVariables =
				(Map<String,Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		
		if (modelAndView == null){
			return;
		}
		//modelAndView.addObject("accessControl", accessControlWrapper);

	
	}
}
