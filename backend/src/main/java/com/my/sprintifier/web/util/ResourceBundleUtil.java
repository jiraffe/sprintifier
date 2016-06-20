package com.my.sprintifier.web.util;


import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * The Class ResourceBundleUtil.
 */
public class ResourceBundleUtil {

	/** The Constant MESSAGE_RESOURCE_BUNDLE. */
	private static final String MESSAGE_RESOURCE_BUNDLE = "messages/messages";
	
	/** The Constant CONSTRAINT_RESOURCE_BUNDLE. */
	private static final String CONSTRAINT_RESOURCE_BUNDLE = "messages/constraints";
	
	/** The instance. */
	private static ResourceBundleUtil instance = new ResourceBundleUtil();
	
	/**
	 * Instantiates a new resource bundle util.
	 */
	private ResourceBundleUtil() {
		
	}

	/**
	 * Gets the single instance of ResourceBundleUtil.
	 *
	 * @return single instance of ResourceBundleUtil
	 */
	public static ResourceBundleUtil getInstance() {
		return instance;
	}
	
	/**
	 * Gets the params msg.
	 *
	 * @param key the key
	 * @param params the params
	 * @return the params msg
	 */
	public static String getParamsMsg(String key, Object... params) {
		return getMsg(key, MESSAGE_RESOURCE_BUNDLE, params);
	}
	
	
	/**
	 * Gets the constraint msg.
	 *
	 * @param key the key
	 * @param params the params
	 * @return the constraint msg
	 */
	public static String getConstraintMsg(String key, Object... params) {
		return getMsg(key, CONSTRAINT_RESOURCE_BUNDLE, params);
	}
	
	/**
	 * Gets the msg.
	 *
	 * @param key the key
	 * @param params the params
	 * @return the msg
	 */
	public static String getMsg(String key, Object... params) {
		return getMsg(key, MESSAGE_RESOURCE_BUNDLE, params);
	}

	/**
	 * Gets the msg.
	 *
	 * @param key the key
	 * @param path the path
	 * @param params the params
	 * @return the msg
	 */
	public static String getMsg(String key, String path, Object... params) {
		return getInstance().getMessage(key, path, params);
	}

	/**
	 * Gets the message.
	 *
	 * @param key the key
	 * @param params the params
	 * @return the message
	 */
	public String getMessage(String key, Object... params) {
		return getMessage(key, MESSAGE_RESOURCE_BUNDLE, params);
	}

	/**
	 * Gets the message.
	 *
	 * @param key the key
	 * @param path the path
	 * @param params the params
	 * @return the message
	 */
	public String getMessage(String key, String path, Object... params) {
		String msg = ResourceBundle.getBundle(path).getString(key);

		if (msg != null && params != null && params.length > 0) {

			msg = MessageFormat.format(msg, params);
		}

		return msg;
	}
	
	/**
	 * Contains message.
	 *
	 * @param key the key
	 * @param path the path
	 * @return the boolean
	 */
	public Boolean containsMessage(String key, String path) {
		Boolean contains = ResourceBundle.getBundle(path).containsKey(key);
		return contains;
	}
	
}
