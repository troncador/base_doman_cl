package cl.doman.springmvc.initializer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import cl.doman.springmvc.security.SecurityFilter;



@Configuration
public abstract class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
	static Logger log =LoggerFactory.getLogger(SpringMvcConfiguration.class);
	//static private String basePath = "/opt/html/troncador/araucana2/";
	
	public abstract File getBasePath();
	//public abstract List<HandlerInterceptor> getInterceptorList();
	@Bean
    public TemplateResolver templateResolver() {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        File file = getBasePath();
        String basePath = file.getPath();

        String path = String.format("%s/%s", basePath, "templates/");
        templateResolver.setPrefix(path);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        
        return templateResolver;
    }
    
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        TemplateResolver templateResolver = templateResolver();
        templateEngine.setTemplateResolver(templateResolver);
        
        return templateEngine;
    }
    
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        SpringTemplateEngine springTemplateEngine = templateEngine();
        viewResolver.setTemplateEngine(springTemplateEngine);
        
        return viewResolver;
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
        messageSource.setBasename("i18n/messages");  
        
        return messageSource;  
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }  
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = getBasePath();
        String basePath = file.getPath();
        String path = String.format("file:%s/%s", basePath, "resources/");
        ResourceHandlerRegistration resourceRegistration = registry.addResourceHandler("/resources/**");
        resourceRegistration.addResourceLocations(path);
    }
    
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//		log.info("se agrego");
//    	for(HandlerInterceptor interceptor : getInterceptorList()){
//    		log.info(interceptor.getClass().getName());
//    		registry.addInterceptor(interceptor);
//    	}
//    	
//    }
}