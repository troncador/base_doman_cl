package cl.doman.j2ee.resource.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.resource.validation.foo.ParamError;
import cl.doman.resource.validation.foo.StringValidation;
import cl.doman.resource.validation.foo.Validation;




public class ServletParamHandler {
	static Logger log = LoggerFactory.getLogger(ServletParamHandler.class);
	private Map<String, String[]> parameterMap;
	
	private Map<String,Validation<?>> validationList = new HashMap<String,Validation<?>>();
	private HttpServletRequest request;

	private ServletFileUpload getUploader(){
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//tamaño a partir del cual se escriben los archivos directamente en disco
		factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
		//File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(new File("/home/troncador/Desktop/tmp/"));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//tamaño máximo aceptado
		upload.setSizeMax(200000l);
		return upload;
	}
	
	//ProgressListener progressListener = new ProgressListener(){

	
	
	private void hasFormFieldError(FileItem formField){
		Validation<?> validation = validationList.get(formField.getFieldName());
		if(validation!=null){
			String value = formField.getString();
			validation.run(value);
		}
	}
	
	
	
	public boolean run() throws FileUploadException{
//		try{
			ServletFileUpload servletFileUpload = getUploader();
		
			List<FileItem> items = servletFileUpload.parseRequest(this.request);
			
			for(FileItem item: items){
				 if (item.isFormField()){
					 //algo(item);
					 
				 } else {

				 }
			}
		return true;
	}
	
	
	public ServletParamHandler(HttpServletRequest request){
		this.request = request;
	}
	
//	private Map<String, String[]> getParameterMap(ServletRequest servletRequest) {
//		@SuppressWarnings("unchecked")
//		Map<String, String[]> result = servletRequest.getParameterMap();
//		return result;
//	}
	
	public String fileItemToString(FileItem item){
		if(!item.isFormField()){
			return String.format("{'formField':%b,'fieldName':'%s','name':'%s','contentType':'%s','inMemory':'%b','size':'%d'}", 
					item.isFormField(),
					item.getFieldName(),
					item.getName(),
					item.getContentType(),
					item.isInMemory(),
					item.getSize());
		} else {
			return String.format("{'formField':%b,'name':'%s','value':'%s'}", 
					item.isFormField(),
					item.getFieldName(),
					item.getString());
		}
	}
	
	
	public String get(String paramName){
		String[] stringArray = parameterMap.get(paramName);
		return stringArray[0];
	}
	
	public boolean hasField(String paramName){
		return parameterMap.containsKey(paramName);
	}
	
	public Validation<?> getValidation(String paramName){
		if(validationList.containsKey(paramName)){
			return validationList.get(paramName);
		} else {
			String[] rawParameter = parameterMap.get(paramName);
			String parameter = (rawParameter != null && rawParameter.length > 0)? 
					rawParameter[0]: null;
			StringValidation validation = new StringValidation(parameter);
			validationList.put(paramName, validation);
			return validation;
		}

	}
	
	public List<ParamError> getFieldErrors(String paramName){
		if(!validationList.containsKey(paramName)){
			return new ArrayList<ParamError>();
		} else {
			Validation<?> validation = validationList.get(paramName);
			//return validation.getErrorList();
			return null;
		}
	}
	
	public boolean hasFieldErrors(String paramName){
		if(!validationList.containsKey(paramName)){
			return false;
		} else {
			Validation<?> validation = validationList.get(paramName);
			return validation.hasError();
		}
	}
	
	public boolean hasError(){
		for(Validation<?> validation: validationList.values()){
			if(validation.hasError()){
				return true;
			}
		}
		return false;
	}
}
