package com.my.sprintifier.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.MissingResourceException;

import javax.persistence.PersistenceException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import net.sf.json.JSONObject;

/**
 * 
 * 
 *
 */
public class ExceptionFilter implements Filter {

	
	private static final String ERROR_PREF = "spinter.error";
	private static final String CONSTRAINT_ERROR_PREF = "sprinter.error.constraint.";

	private static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

	private ResourceBundleUtil resourceBundleUtil = ResourceBundleUtil.getInstance();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		
		try {
			chain.doFilter(request, response);
		} catch (PersistenceException e) {
			e.printStackTrace();
			writeError(res, e);
		} catch (ServletException e) {
			e.printStackTrace();
			writeError(res, e);
		} catch (Exception e) {
			writeError(res, e);
			e.printStackTrace();
		}
	}
	
	public void writeError(HttpServletResponse res, Exception e) throws IOException {
		res.setStatus(500);
		res.setContentType(JSON_CONTENT_TYPE);
		
        PrintWriter printout = res.getWriter();

        JSONObject JObject = new JSONObject(); 
        JObject.put("success", false); 
        JObject.put("message", getMessage(e)); 

        printout.print(JObject);
        printout.flush();
	}
	
	private Object getMessage(Exception e) {
		String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();

		if(e.getCause() != null && (
				e.getCause().getClass().equals(DataIntegrityViolationException.class) ||
				e.getCause().getClass().equals(ConstraintViolationException.class) )
				)	{
			return getPersistenceErrorMessage(e.getCause());
		}
		
		try {
			return getMessageFromProperties(message);
		} catch (MissingResourceException mre) {
			return message;
		}
	}

	private Object getMessageFromProperties(String message) {
		if(message == null) {			
			return resourceBundleUtil.getMessage(ERROR_PREF);
		} else {
			return resourceBundleUtil.getMessage(message);
		}
	}

	private Object getPersistenceErrorMessage(Throwable throwable) {
		while(true) {
			if(throwable.getClass().equals(ConstraintViolationException.class)) {
				return getPersistenceErrorMessage((ConstraintViolationException) throwable);
			}
			throwable = throwable.getCause();
		}
	}
	
	private Object getPersistenceErrorMessage(ConstraintViolationException e) {
		String constraintName = CONSTRAINT_ERROR_PREF + e.getConstraintName();
		return ResourceBundleUtil.getConstraintMsg(constraintName);
	}
}
