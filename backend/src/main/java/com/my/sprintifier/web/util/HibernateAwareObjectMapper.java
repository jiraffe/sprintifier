package com.my.sprintifier.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper{
	 
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5806420240168104981L;

	/**
	 * Instantiates a new hibernate aware object mapper.
	 */
	public HibernateAwareObjectMapper() {
	    Hibernate4Module hm = new Hibernate4Module();
	    hm.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
	    enable(SerializationFeature.INDENT_OUTPUT);
	    enable(SerializationFeature.WRAP_EXCEPTIONS);
	    disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	    registerModule(hm);
	}

	
	
}
