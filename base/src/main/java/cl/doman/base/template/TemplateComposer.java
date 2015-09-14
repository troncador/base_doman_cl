package cl.doman.base.template;

import java.util.Map;

public interface TemplateComposer {
	String process(Map<String, ?> variables,String templateName);
}
