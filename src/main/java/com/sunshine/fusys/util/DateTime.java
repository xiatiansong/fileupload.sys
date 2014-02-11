package com.sunshine.fusys.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
/**
* 创建人：fantasy
* 创建时间:2013/3/13
* 功能描述:时间工具类
*/
public class DateTime {
	public static final int YEAR = 1; //获取年;
	static final String YEAR_STR = "yyyy";
	public static final int MONTH = 2; //获取月;
	static final String MONTH_STR = "MM";
	public static final int DATE =3 ; //获取天;
	static final String DATE_STR = "dd";
	public static final int HOUR =4;//获取小时; 
	static final String HOUR_STR = "HH";
	public static final int MINUTE = 5;//获取分钟;
	static final String MINUTE_STR = "mm";
	public static final int SECOND = 6;//获取秒钟;
	static final String SECOND_STR = "ss";
    
	public static final int YEAR_MONTH_DATE = 7;//获取 年 - 月 - 日;
	public static final int YEAR_MONTH_DATE_HOUR = 8;//获取 年 - 月 - 日 小时;
	public static final int YEAR_MONTH_DATE_HOUR_MINUTE = 9;//获取 年 - 月 - 日 小时:分钟;
	
	public static final int HOUR_MINUTE = 10;//  小时:分钟;
	
	public static final int DATE_HOUR = 11;//日 小时;
	
	//获取时间
    public static String getTime(int obj){
    	switch (obj) {
		case YEAR:
			return toTime(YEAR_STR);
		case MONTH:
			return toTime(MONTH_STR);
		case DATE:
			return toTime(DATE_STR);
		case HOUR:
			return toTime(HOUR_STR);
		case MINUTE:
			return toTime(MINUTE_STR);
		case SECOND:
			return toTime(SECOND_STR);
		case YEAR_MONTH_DATE:
			return toTime(YEAR_STR+"-"+MONTH_STR+"-"+DATE_STR);
		case YEAR_MONTH_DATE_HOUR:
			return toTime(YEAR_STR+"-"+MONTH_STR+"-"+DATE_STR+" "+HOUR_STR);
		case YEAR_MONTH_DATE_HOUR_MINUTE:
			return toTime(YEAR_STR+"-"+MONTH_STR+"-"+DATE_STR+" "+HOUR_STR+":"+MINUTE_STR);
		case HOUR_MINUTE:
			return toTime(HOUR_STR+":"+MINUTE_STR);
		case DATE_HOUR:
			return toTime(DATE_STR+" "+HOUR_STR);
		default:
			return toTime();
		}
    	 
    }
	 public static void main(String[] args) {
		 System.out.println(DateTime.isDateTime("2013-11-22 12:00:21"));
		 System.out.println(DateTime.parseDate("2013-9-15 00:00:00", "yyyy-MM-dd HH:mm:ss"));
	    System.out.println(DateTime.toTime("yyyyMMddHHmmss"));
	    Date start = DateTime.parseDate("2013-9-15", "yyyy-MM-dd");
		Date end = DateTime.parseDate("2013-9-11", "yyyy-MM-dd");
		if(start.getTime() >= end.getTime()){
			System.out.println("1>=2");
		}
		System.out.println(getDaysBetweenTwoTimes("2013-9-15","2013-9-15"));
	}
	 
	 /**
     * 检查字符串是否为日期
     * @author RASCAL
     */
    public static boolean isDate(Object value)
    {
        return isDateFormat(value, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
    }
    
    /**
     * 检查字符串是否为日期时间类型
     * @author RASCAL
     */
    public static boolean isDateTime(Object value)
    {
        return isDateFormat(value, "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    }
    
    /**
     * 检查字符串是否为指定的日期类型
     * @param fromatReg 格式正则表达式
     * @author RASCAL
     */
    public static boolean isDateFormat(Object value, String fromatReg)
    {
        Pattern pattern = Pattern.compile(fromatReg);
        if (Validation.isNULL(value) || !pattern.matcher(value.toString()).matches())
        	return false;
        else
        	return true;
    }
    
    /**
     * 解析日期类型，格式"yyyyMMdd"
     * @author RASCAL
     */
   /* public static Date toDate(Object value)
    {
        return parseDate(value, "yyyy-MM-dd");
    }*/
 
    public static String toDate(String  format){
    	if ( Validation.isNULL(format))
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	else
    		return new SimpleDateFormat(format).format(new Date());
    }
    
    public static String toDate(String  format,Date date){
    	if ( Validation.isNULL(format))
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    	else
    		return new SimpleDateFormat(format).format(date);
    }


    /**
     * 解析日期时间类型，格式"yyyyMMdd hh:mm:ss"
     * @author RASCAL
     */
    public static Date toDateTime(Object value)
    {
        return parseDate(value, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 解析日期类型
     * @param fromat 日期格式
     * @author RASCAL
     */
    public static Date parseDate(Object value, String fromat)
    {
        //解析日期格式
        try
        {
            DateFormat df =  new SimpleDateFormat(fromat);
            return df.parse(value.toString());
        }
        catch (Exception ex)
        {
//            logger.warn("字符串[{}]转日期格式[{}]转换错误!", str, fromat);
        }
        return null;
    }
    
    /**
     * 
     * @param format 日期格式
     * @return 当日日期
     * @author RASCAL
     */
    public static String toTime(String  format){
    	if (Validation.isNULL(format)){
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	}else{
    		return new SimpleDateFormat(format).format(new Date());
    	}
    }

    public static String toTime( ){
    	return toTime(null);
    }
    
    /**
     * 获取两个时间的间隔天数
     * @param dateFrom
     * @param dateEnd
     * @return int
     * @author fantasy 
     * @date 2013-11-6
     */
    public static int getDaysBetweenTwoTimes(Object dateFrom, Object dateEnd) {
        Date dtFrom = parseDate(dateFrom, "yyyy-MM-dd");
        Date dtEnd = parseDate(dateEnd, "yyyy-MM-dd");
        long begin = dtFrom.getTime();
        long end = dtEnd.getTime();
        long inter = end - begin;
        if (inter < 0) {
            inter = inter * (-1);
        }
        long dateMillSec = 24 * 60 * 60 * 1000;
        Long dateCnt =  inter / dateMillSec;
        long remainder = inter % dateMillSec;
        if (remainder != 0) {
            dateCnt++;
        }
        return dateCnt.intValue();
    }
    
    /**
     * Long转为日期，结果为String
     */
    public static String longToDate(Long value, String format){
    	 try{
    		 DateFormat df =  null;
    		 if (Validation.isNULL(format)){
    			 df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		 }else{
    			 df =  new SimpleDateFormat(format);
    		 }
    		 Date dt = new Date(value);  
             return df.format(dt);
         }catch (Exception ex){}
         return null;
    }
}