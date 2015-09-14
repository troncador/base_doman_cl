package cl.doman.spring.textComposer;

import java.io.StringWriter;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cl.doman.base.template.TemplateComposer;

public class ThymeleafTemplateComposer implements TemplateComposer {

	private TemplateEngine templateEngine;

	
	public ThymeleafTemplateComposer(TemplateEngine templateEngine){
		this.templateEngine = templateEngine;
	}

	public String process(Map<String, ?> variables, String templateName) {
		Context context = new Context();
		context.setVariables(variables);
		
		StringWriter writer = new StringWriter();
		templateEngine.process(templateName, context, writer);
		return  writer.toString();
	}

}
