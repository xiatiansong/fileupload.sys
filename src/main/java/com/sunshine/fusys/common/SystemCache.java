package com.sunshine.fusys.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 目前用于缓存
 * @author fantasy
 */
public class SystemCache {
	public static final String IP_AREA_LIST = "IP_AREA_LIST";
	private static Map<String, Object> map = new HashMap<String, Object>();

	public static Object getCache(String key) {
		if (map.get(key) == null)
			return null;
		return map.get(key);
	}

	public static void setCache(String key, Object value) {
		synchronized (map) {
			map.put(key, value);
		}
	}

	public static void remove(String key) {
		synchronized (map) {
			map.remove(key);
		}
	}

	public static void removeAll(String key) {
		synchronized (map) {
			map.clear();
		}
	}

	public static int size() {
		return map.size();
	}

}