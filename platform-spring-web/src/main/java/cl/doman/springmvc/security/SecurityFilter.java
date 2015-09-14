package cl.doman.springmvc.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;

public interface SecurityFilter {
	void run(HttpServletRequest request, HandlerMethod handlerMethod) throws Exception;
}
