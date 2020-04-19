package com.share.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * �����ڶ���ת�����ַ���
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * ���ַ��������ƶ��ĸ�ʽת�������ڶ���
	 * @param strDate �ַ���
	 * @param pattern  ��ʽ���ַ���
	 * @return ���ڶ���
	 * @throws ParseException
	 */
	public static  Date strToDate(String strDate,String pattern) throws ParseException{
		 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		 return sdf.parse(strDate);
	}

}
