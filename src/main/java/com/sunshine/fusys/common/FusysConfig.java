package com.sunshine.fusys.common;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-9-11 <br>
 * 功能描述：加载constant.properties配置 <br>
 * 
 */
public class FusysConfig extends BaseConfig {

	/** 上传系统标志 当前系统 1-外网上传，2-内网上传 **/
	private static int fusysType;
	
	private static String uploadFilePath;
	
	private static String FILE_PATH = "config/constant.properties";

	static {
		loadValue(FILE_PATH, new FusysConfig());
	}

	/**
	 * 重新加载配置
	 * 
	 * @author fantasy
	 * @date 2013-9-23
	 */
	public static void reloadValue() {
		reloadValue(FILE_PATH, new FusysConfig());
	}

	public static int getFusysType() {
		return fusysType;
	}

	public static void setFusysType(int fusysType) {
		FusysConfig.fusysType = fusysType;
	}

	public static String getUploadFilePath() {
		return uploadFilePath;
	}

	public static void setUploadFilePath(String uploadFilePath) {
		FusysConfig.uploadFilePath = uploadFilePath;
	}
}