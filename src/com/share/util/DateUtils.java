package com.share.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 将日期对象转换成字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 将字符串按照制定的格式转换成日期对象
	 * @param strDate 字符串
	 * @param pattern  格式化字符串
	 * @return 日期对象
	 * @throws ParseException
	 */
	public static  Date strToDate(String strDate,String pattern) throws ParseException{
		 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		 return sdf.parse(strDate);
	}

}
