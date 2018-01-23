package com.epoint.utils;

import java.util.ResourceBundle;

public class ConfigUtil {
	public static String getJDBCConfigValue(String configname) {
		String result = null;
		try {
			ResourceBundle resBundle = ResourceBundle.getBundle("jdbc");
			result = resBundle.getString(configname);
			if ("".equals(result) && result != null) {
				result = result.trim();
			}
		} catch (Exception e) {
		}
		return result;
	}
}
