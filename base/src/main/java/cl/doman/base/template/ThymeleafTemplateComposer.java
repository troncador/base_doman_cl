package cl.doman.base.template;


import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import cl.doman.base.initializator.SystemInitializator;
import cl.doman.base.initializator.SystemInitializatorException;




public class ThymeleafTemplateComposer  implements TemplateComposer{
	static Logger log =LoggerFactory.getLogger(ThymeleafTemplateComposer.class);
	private TemplateEngine templateEngine;
	
	public ThymeleafTemplateComposer(String path, boolean cacheable){
		FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(path);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(cacheable);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
		
	}

	public String process(Map<String, ?> variables, String templateName) {
		/**
		 * TODO: agregar al context el locale
		 */
		final Context ctx = new Context();
		ctx.setVariables(variables);
		return templateEngine.process(templateName, ctx);
	}
}