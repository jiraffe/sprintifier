package com.my.sprintifier.web.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * The Class ResponseMapUtil.
 */
public class ResponseMapUtil {
	
	/** The Constant RESPONCE_CODE_NODE. */
	private static final String RESPONCE_CODE_NODE = "code";
	
	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";
	
	/** The Constant DATA_NODE. */
	private static final String DATA_NODE = "data";
	
	/** The Constant MESSAGE_NODE. */
	private static final String MESSAGE_NODE = "message";
	
	/** The Constant SMTH_WENT_WRONG. */
	private static final Object SMTH_WENT_WRONG = "Sorry, something went wrong";

	
	/**
	 * Map success.
	 *
	 * @param data the data
	 * @return the map
	 */
	public static Map<Object, Object> mapSuccess(Object data) {
		return mapSuccess(data, SUCCESS);
	}
	
	/**
	 * Map success.
	 *
	 * @param <T> the generic type
	 * @param <U> the generic type
	 * @param data the data
	 * @param whetherToConvert the whether to convert
	 * @return the map
	 */
	public static <T, U> Map<Object, Object> mapSuccess(Map<T, U> data, Boolean whetherToConvert) {
		if(whetherToConvert) {
			return mapSuccess(data);
		} else {
			return mapSuccess(data, SUCCESS);
		}
	}
	
	/**
	 * Map success.
	 *
	 * @param msg the msg
	 * @return the map
	 */
	public static Map<Object, Object> mapSuccess(String msg) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(MESSAGE_NODE, msg);
		modelMap.put(SUCCESS, true);
		
		return modelMap;
	}
	
	/**
	 * Map success.
	 *
	 * @param msg the msg
	 * @param textAsData the text as data
	 * @return the map
	 */	
	public static Map<Object, Object> mapSuccess(String msg, boolean textAsData) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(DATA_NODE, msg);
		modelMap.put(SUCCESS, true);
		
		return modelMap;
	}
	
	/**
	 * Map success.
	 *
	 * @return the map
	 */
	public static Map<Object, Object> mapSuccess() {
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(SUCCESS, true);
		
		return modelMap;
	}
	
	/**
	 * Map success.
	 *
	 * @param data the data
	 * @param msg the msg
	 * @return the map
	 */
	public static Map<Object, Object> mapSuccess(Object data, String msg) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(DATA_NODE, data);
		modelMap.put(MESSAGE_NODE, msg);
		modelMap.put(SUCCESS, true);
		
		return modelMap;
	}
	
	public static Map<Object, Object> mapSuccess(JSONObject data, String msg) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(DATA_NODE, data);
		modelMap.put(MESSAGE_NODE, msg);
		modelMap.put(SUCCESS, true);
		
		return modelMap;
	}
	/**
	 * Map error.
	 *
	 * @param errorMsg the error msg
	 * @return the map
	 */
	public static Map<Object, Object> mapError(String errorMsg) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(MESSAGE_NODE, errorMsg);
		modelMap.put(SUCCESS, false);
		
		return modelMap;
	}
	
	/**
	 * Map error.
	 *
	 * @param errorMsg the error msg
	 * @param data the data
	 * @return the map
	 */
	public static Map<Object, Object> mapError(String errorMsg, Object data) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(MESSAGE_NODE, errorMsg);
		modelMap.put(DATA_NODE, data);
		modelMap.put(SUCCESS, false);
		
		return modelMap;
	}
	
	/**
	 * Map error.
	 *
	 * @param errorMsg the error msg
	 * @param errCode the err code
	 * @return the map
	 */
	public static Map<Object, Object> mapError(String errorMsg, int errCode) {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(RESPONCE_CODE_NODE, errCode);
		modelMap.put(MESSAGE_NODE, errorMsg);
		modelMap.put(SUCCESS, false);
		
		return modelMap;
	}
	
	/**
	 * Map error.
	 *
	 * @return the error map
	 */
	public static Map<Object, Object> mapError() {
		
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		modelMap.put(MESSAGE_NODE, SMTH_WENT_WRONG);
		modelMap.put(SUCCESS, false);
		
		return modelMap;
	}
}