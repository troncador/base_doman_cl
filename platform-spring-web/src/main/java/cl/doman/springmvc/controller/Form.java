package cl.doman.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import cl.doman.db.model.table.BaseTable;
import cl.doman.resource.validation.ParamHandler;
import cl.doman.springmvc.HttpStatusErrorException;

public interface Form<T extends BaseTable<PK>,PK> {
	public String editSubmit(
			HttpServletRequest request,
			ModelMap model
	) throws HttpStatusErrorException;

	public void validate(ParamHandler params) throws Exception ;

	public T conversion(ParamHandler params) throws Exception;
	public T action(T t) throws Exception;
	public String hasError(ModelMap model, ParamHandler paramHandler);
	public String hasSuccess(ModelMap model, T t);
}
