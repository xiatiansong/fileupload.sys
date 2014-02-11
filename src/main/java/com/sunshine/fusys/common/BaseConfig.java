package com.sunshine.fusys.common;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sunshine.fusys.util.PropertiesUtil;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-8-7 <br>
 * 功能描述：配置文件加载的 基类,
 * 注意:若项目要加载HttpUtil类的getUrl或sendGetRequest(参数isAddMd5ToUrl为true)方法时,<br>
 * 则必须继承此类且在配置文件中配置<code>ConfigParam</code>类中的code字符串, <br>
 * 且子类不能含有与code一样的字段<br>
 */
public abstract class BaseConfig {

	private static final Logger LOG = Logger.getLogger(BaseConfig.class);

	/** 缓存配置文件中配置的, 但子类中没有对应字段的参数 **/
	private static Map<String, String> configParamsMap = new ConcurrentHashMap<String, String>();

	/**
	 * 加载属性文件中的值
	 * 
	 * @param filePath
	 * @param baseConfig
	 * @date:2013-8-7
	 * 
	 */
	protected static void loadValue(String filePath, BaseConfig baseConfig) {
		InputStream is = null;
		try {
			is = baseConfig.getClass().getClassLoader().getResourceAsStream(filePath);
			Properties prop = new Properties();
			prop.load(is);

			Map<String, String> map = PropertiesUtil.convertToMap(prop);
			map = processMapKey(map);

			Field[] fields = baseConfig.getClass().getDeclaredFields();
			String value = "";
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					continue;
				}

				value = map.remove(field.getName().toUpperCase());
				if (value == null) {
					continue;
				}
				field.setAccessible(true);
				if (field.getType() == boolean.class) {
					field.setBoolean(baseConfig, Boolean.parseBoolean(value));
				} else if (field.getType() == int.class) {
					field.setInt(baseConfig, Integer.parseInt(value));
				} else if (field.getType() == long.class) {
					field.setLong(baseConfig, Long.parseLong(value));
				} else {
					field.set(baseConfig, value);
				}
			}

			if (map.size() > 0) {
				configParamsMap.putAll(map);
			}
		} catch (Exception e) {
			LOG.error("属性文件加载失败  " + filePath + " caused by " + e.toString());
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * 重新加载配置文件
	 * 
	 * @param filePath
	 * @param baseConfig
	 *            void
	 * @author fantasy
	 * @date 2013-9-23
	 */
	protected static void reloadValue(String filePath, BaseConfig baseConfig) {
		configParamsMap.clear();
		loadValue(filePath, baseConfig);
	}

	/**
	 * 获取缓存配置文件中配置的, 但子类中没有对应字段的参数
	 * 
	 * @param key
	 * @return String
	 * @date:2013-8-7
	 */
	public static String getConfigParam(String key) {
		if (key == null) {
			return null;
		}
		return configParamsMap.get(processKey(key));
	}

	/**
	 * 处理Map中的键值为大写并去掉下划线
	 * 
	 * @param map
	 * @return Map<String,String>
	 * @date:2013-8-7
	 */
	private static Map<String, String> processMapKey(Map<String, String> map) {
		Map<String, String> result = new HashMap<String, String>(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			result.put(processKey(entry.getKey()), entry.getValue());
		}
		return result;
	}

	private static String processKey(String key) {
		return key.toUpperCase().replaceAll("_", "");
	}
}
