package cl.doman.springmvc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.doman.db.QueryException;
import cl.doman.db.model.table.BaseTable;
import cl.doman.db.query.dao.EntityDaoCriteria;
import cl.doman.resource.validation.ParamHandler;
import cl.doman.spring.beanpopulator.BeanPopulatorException;
import cl.doman.springmvc.HttpStatusErrorException;
import cl.doman.springmvc.datatable.PrepareQueryDataTable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

public abstract class FormHandler<T extends BaseTable<PK> ,PK extends Serializable> implements Form<T,PK>{
	static Logger log = LoggerFactory.getLogger(FormHandler.class);
	private static final String jsonMIME = "application/json";
	
	
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	final public String list() throws QueryException {
		return getBaseURL("list");
	}
	
	
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	final public String editSubmit(
			HttpServletRequest request,
			ModelMap model
				) throws HttpStatusErrorException {
		Map<String,String[]> parameterMap = request.getParameterMap();
		ParamHandler paramHandler = new ParamHandler(parameterMap);
		try {
			validate(paramHandler);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new HttpStatusErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(paramHandler.hasError()){
			return this.hasError(model,paramHandler);
		}
		T t = null;
		try{
			t = conversion(paramHandler); 
			t = action(t);
		} catch(Exception e){
			log.error(e.getMessage(),e);
			throw new HttpStatusErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return this.hasSuccess(model,t);
	}
	protected abstract String getBaseURL(String path);
	protected abstract EntityDaoCriteria<T, PK> getEntityDaoCriteria();
	public abstract  JsonObject convert(T entity);
	
	@RequestMapping(
			value ="list.json",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	final public void listJson(
			HttpServletRequest request,
			HttpServletResponse response) throws QueryException {
		try {
			response.setContentType(jsonMIME);
		    response.setCharacterEncoding(CharEncoding.UTF_8);
			OutputStream outputStream = response.getOutputStream();
			@SuppressWarnings("unchecked")
			Map<String,String[]> params = (Map<String,String[]>)request.getParameterMap();
			
			PrepareQueryDataTable<T> prepareQuery = new PrepareQueryDataTable<T>(params);
			Integer draw = prepareQuery.getDraw();
			
			EntityDaoCriteria<T, PK> query =  getEntityDaoCriteria();
			long countTotal = query.count();
			long filterCountTotal = query.count(prepareQuery);
			
			List<T> list =  query.find(prepareQuery);
			toStream(list,draw, countTotal,filterCountTotal,outputStream);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} catch (BeanPopulatorException e) {
			log.error(e.getMessage(),e);
		}
	}
	

	
	private void toStream(List<T> list,int draw, long countTotal,long filterCountTotal, OutputStream outputStream) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		JsonWriter writer = null;
		
		try{
			OutputStreamWriter outputStreamWriter = 
					new OutputStreamWriter(outputStream, CharEncoding.UTF_8);
			writer = new JsonWriter(outputStreamWriter);
			JsonObject rootObject = new JsonObject();
			rootObject.addProperty("recordsTotal", countTotal);
			rootObject.addProperty("recordsFiltered", filterCountTotal);
			JsonArray jsonArray = new JsonArray();
			for (T entity : list){
				JsonObject jsonObject = convert(entity);
				jsonArray.add(jsonObject);
			}
			rootObject.add("data", jsonArray);
			gson.toJson(rootObject,writer);
		} finally {
			writer.close();
		}
	
	}
}
