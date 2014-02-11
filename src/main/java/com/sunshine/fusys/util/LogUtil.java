package com.sunshine.fusys.util;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2014-2-10 <br>
 * 功能描述： 日志工具类<br>
 */
public class LogUtil {
	public static final String LOG_SPLIT = " ";
	public static final String LOG_MARKS_QUOTATION = "\"";

	/**
	 * 日志信息获取
	 * 
	 * @param functionName
	 *            功能说明
	 * @param status
	 *            状态
	 * @param inputParams
	 *            输入参数
	 * @param outputParams
	 *            输出参数
	 * @param exceptionMsg
	 *            异常信息
	 * @return String
	 * @author:Jiyong.Wei
	 * @date:2013-4-9
	 */
	public static String getLogStr(String functionName, String status,
			Object inputParams, Object outputParams, String exceptionMsg) {
		StringBuffer sb = new StringBuffer();
		sb.append(functionName).append(LOG_SPLIT);

		sb.append(status).append(LOG_SPLIT);

		sb.append(LOG_MARKS_QUOTATION);
		sb.append(inputParams != null ? inputParams.toString() : "");
		sb.append(LOG_MARKS_QUOTATION);
		sb.append(LOG_SPLIT);

		sb.append(LOG_MARKS_QUOTATION);
		sb.append(outputParams != null ? outputParams.toString() : "");
		sb.append(LOG_MARKS_QUOTATION);
		sb.append(LOG_SPLIT);

		sb.append(LOG_MARKS_QUOTATION);
		sb.append(exceptionMsg != null ? exceptionMsg : "");
		sb.append(LOG_MARKS_QUOTATION);
		return sb.toString();
	}
}
